package service;

import pl.project.pvpredictbackend.dto.DailyInformationDto;
import pl.project.pvpredictbackend.dto.OpenMeteoResponseDto;
import pl.project.pvpredictbackend.dto.WeeklySummaryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.project.pvpredictbackend.service.OpenMeteoClient;
import pl.project.pvpredictbackend.service.PredictService;
import pl.project.pvpredictbackend.util.PvEnergyCalculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PredictServiceTest {

    private PredictService predictService;
    private OpenMeteoClient openMeteoClient;

    @BeforeEach
    void setUp() {
        openMeteoClient = mock(OpenMeteoClient.class);
        predictService = new PredictService(openMeteoClient);
    }

    @Test
    void testGetWeekForecast_returnsCorrectData() {
        OpenMeteoResponseDto mockResponse = new OpenMeteoResponseDto();
        OpenMeteoResponseDto.Daily daily = new OpenMeteoResponseDto.Daily();
        daily.setTime(List.of("2025-06-20"));
        daily.setWeather_code(List.of(1));
        daily.setTemperature_2m_min(List.of(12.0));
        daily.setTemperature_2m_max(List.of(25.0));
        daily.setSunshine(List.of(10800.0)); // 3h
        daily.setPressure_msl(List.of(1012.0));
        mockResponse.setDaily(daily);

        when(openMeteoClient.getWeeklyForecast(50.0, 20.0)).thenReturn(mockResponse);

        List<DailyInformationDto> forecast = predictService.getWeekForecast(50.0, 20.0);

        assertEquals(1, forecast.size());
        DailyInformationDto dto = forecast.get(0);
        assertEquals(12.0, dto.getMinTemperature());
        assertEquals(25.0, dto.getMaxTemperature());
        assertEquals(PvEnergyCalculator.calculateGeneratedEnergy(3.0), dto.getGeneratedEnergy());
    }

    @Test
    void testGetSummary_returnsCorrectAveragesAndSummary() {
        OpenMeteoResponseDto mockResponse = new OpenMeteoResponseDto();
        OpenMeteoResponseDto.Daily daily = new OpenMeteoResponseDto.Daily();

        daily.setPressure_msl(List.of(1010.0, 1020.0));
        daily.setSunshine(List.of(7200.0, 14400.0)); // 2h, 4h
        daily.setTemperature_2m_min(List.of(10.0, 12.0));
        daily.setTemperature_2m_max(List.of(20.0, 22.0));
        daily.setWeather_code(List.of(51, 1));

        mockResponse.setDaily(daily);
        when(openMeteoClient.getWeeklyForecast(anyDouble(), anyDouble())).thenReturn(mockResponse);

        WeeklySummaryDto summary = predictService.getSummary(50.0, 20.0);

        assertEquals(1015.0, summary.getAverageWeekPressure());
        assertEquals(3.0, summary.getAverageWeekSunExposureHours());
        assertEquals(10.0, summary.getMinWeekTemperature());
        assertEquals(22.0, summary.getMaxWeekTemperature());
        assertEquals("bez opadów", summary.getGeneralWeatherSummary());
    }
}
