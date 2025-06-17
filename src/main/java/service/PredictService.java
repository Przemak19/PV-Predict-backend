package service;

import dto.DailyInformationDto;
import dto.OpenMeteoResponseDto;
import dto.WeeklySummaryDto;
import org.springframework.stereotype.Service;
import util.PvEnergyCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for retrieving, processing, and transforming weather data into formats usable by the controller.
 */
@Service
public class PredictService {

    private final OpenMeteoClient openMeteoClient;

    public PredictService(OpenMeteoClient openMeteoClient) {
        this.openMeteoClient = openMeteoClient;
    }

    /**
     * Retrieves and processes the 7-day weather forecast with calculated energy.
     *
     * @param latitude user geographic latitude
     * @param longitude user geographic longitude
     * @return list of daily weather forecasts with calculated energy values
     */
    public List<DailyInformationDto> getWeekForecast(double latitude, double longitude) {

        OpenMeteoResponseDto response = openMeteoClient.getWeeklyForecast(latitude, longitude);

        List<DailyInformationDto> forecast = new ArrayList<>();

        // map data to DTO for every day
        for(int i = 0; i < response.getDaily().getTime().size(); i++) {

            LocalDate date = LocalDate.parse(response.getDaily().getTime().get(i));
            int weatherCode = response.getDaily().getWeather_code().get(i);
            double minTemp = response.getDaily().getTemperature_2m_min().get(i);
            double maxTemp = response.getDaily().getTemperature_2m_max().get(i);
            double sunshineHours = response.getDaily().getSunshine().get(i) / 3600.0; // transform seconds to hours

            double generatedEnergy = PvEnergyCalculator.calculateGeneratedEnergy(sunshineHours);

            DailyInformationDto dto = new DailyInformationDto();
            dto.setDate(date);
            dto.setWeatherCode(weatherCode);
            dto.setMinTemperature(minTemp);
            dto.setMaxTemperature(maxTemp);
            dto.setGeneratedEnergy(generatedEnergy); // in kWh

            forecast.add(dto);
        }

        return forecast;
    }

    /**
     * Computes a weekly weather summary for the given location.
     *
     * @param latitude user geographic latitude
     * @param longitude user geographic longitude
     * @return aggregated weather statistics for the week
     */
    public WeeklySummaryDto getSummary(double latitude, double longitude) {

        OpenMeteoResponseDto response = openMeteoClient.getWeeklyForecast(latitude, longitude);

        List<Double> pressure = response.getDaily().getPressure_msl();
        List<Double> sunshine = response.getDaily().getSunshine();
        List<Double> minTemps = response.getDaily().getTemperature_2m_min();
        List<Double> maxTemps = response.getDaily().getTemperature_2m_max();
        List<Integer> weatherCodes = response.getDaily().getWeather_code();

        // calculate average pressure
        double avgPressure = pressure.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        // calculate average sunshine
        double avgSunshineHours = sunshine.stream().mapToDouble(s -> s / 3600.0).average().orElse(0);

        // find minimum temperature
        double minTemperature = minTemps.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        // find maximum temperature
        double maxTemperature = maxTemps.stream().mapToDouble(Double::doubleValue).max().orElse(0);

        // count days with precipitation using method isRainyWeather
        long precipitationDaysCount = weatherCodes.stream()
                .filter(this::isPrecipitation)
                .count();

        String summary = (precipitationDaysCount >= 4) ? "z opadami" : "bez opadów";

        WeeklySummaryDto summaryDto = new WeeklySummaryDto();

        summaryDto.setAverageWeekPressure(avgPressure);
        summaryDto.setAverageWeekSunExposureHours(avgSunshineHours);
        summaryDto.setMinWeekTemperature(minTemperature);
        summaryDto.setMaxWeekTemperature(maxTemperature);
        summaryDto.setGeneralWeatherSummary(summary);

        return summaryDto;
    }

    /**
     * Simple method to determine if given weather code is precipitation.
     * (Based on Open-Meteo docs) Codes 51-67, 80-82 is rain, 71-77 is snow, 85-86 is snowfall.
     */
    private boolean isPrecipitation(int weatherCode) {

        return (weatherCode >= 51 && weatherCode <= 67) ||
                (weatherCode >= 80 && weatherCode <= 82) ||
                (weatherCode >= 71 && weatherCode <= 77) ||
                (weatherCode >= 85 && weatherCode <= 86);
    }
}
