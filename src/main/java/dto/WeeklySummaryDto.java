package dto;

import lombok.Data;

/**
 * Data Transfer Object representing weekly summary statistics.
 */
@Data
public class WeeklySummaryDto {

    private double averageWeekPressure;
    private double averageWeekSunExposureHours;
    private double minWeekTemperature;
    private double maxWeekTemperature;
    private String generalWeatherSummary;
}
