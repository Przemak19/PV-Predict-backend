package service;

import dto.OpenMeteoResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * HTTP client for fetching weather data from Open-Meteo API.
 */
@Component
public class OpenMeteoClient {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Builds the URI and performs the HTTP request to Open-Meteo.
     *
     * @param latitude user geographic latitude
     * @param longitude user geographic longitude
     * @return parsed API response containing weather forecast data
     */
    public OpenMeteoResponseDto getWeeklyForecast(double latitude, double longitude) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.open-meteo.com")
                .path("/v1/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("daily", "temperature_2m_min,temperature_2m_max,weather_code,sunshine_duration,pressure_msl")
                .queryParam("timezone", "auto")
                .build();

        String url = uriComponents.toUriString();

        return restTemplate.getForObject(url, OpenMeteoResponseDto.class);
    }
}
