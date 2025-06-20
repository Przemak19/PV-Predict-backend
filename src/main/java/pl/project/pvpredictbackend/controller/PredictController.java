package pl.project.pvpredictbackend.controller;

import pl.project.pvpredictbackend.dto.DailyInformationDto;
import pl.project.pvpredictbackend.dto.WeeklySummaryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.project.pvpredictbackend.service.PredictService;

import java.util.List;

/**
 * REST controller exposing endpoints to retrieve solar forecast and weather summaries for a given location.
 */
@RestController
@RequestMapping("/api/predict")
public class PredictController {

    private final PredictService predictService;

    public PredictController(PredictService predictService) {
        this.predictService = predictService;
    }

    /**
     * Endpoint to seven days forecast
     * @param latitude user geographic latitude
     * @param longitude user geographic longitude
     */
    @GetMapping("/forecast")
    public List<DailyInformationDto> get7DayForecast(
            @RequestParam double latitude,
            @RequestParam double longitude) {
        return predictService.getWeekForecast(latitude, longitude);
    }

    /**
     * Summary of the week
     * @param latitude user geographic latitude
     * @param longitude user geographic longitude
     */
    @GetMapping("/summary")
    public WeeklySummaryDto getWeeklySummary(
            @RequestParam double latitude,
            @RequestParam double longitude) {
        return predictService.getSummary(latitude, longitude);
    }
}
