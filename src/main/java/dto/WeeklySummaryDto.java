package dto;

import lombok.Data;

/**
 * Data Transfer Object representing weekly summary statistics.
 */
@Data
public class WeeklySummaryDto {

    private double averageWeekPressure;          // hPa
    private double averageWeekSunExposureHours;  // hours
    private double minWeekTemperature;           // celsius
    private double maxWeekTemperature;           // celsius
    private String generalWeatherSummary;
}
