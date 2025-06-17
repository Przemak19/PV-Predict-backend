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

        return null;
    }
}
