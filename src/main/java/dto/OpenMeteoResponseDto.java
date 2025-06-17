package dto;

import lombok.Data;

import java.util.List;

/**
 * DTO mapping the structure of the response returned by the Open-Meteo API.
 */
@Data
public class OpenMeteoResponseDto {

    private Daily daily;

    /**
     * Holds lists of weather parameters such as time, weather code, max and min temperatures, sunshine duration, and pressure.
     */
    @Data
    public static class Daily {
        private List<String> time;
        private List<Integer> weather_code;
        private List<Double> temperature_2m_min;
        private List<Double> temperature_2m_max;
        private List<Double> sunshine; // in seconds
        private List<Double> pressure_msl;
    }
}
