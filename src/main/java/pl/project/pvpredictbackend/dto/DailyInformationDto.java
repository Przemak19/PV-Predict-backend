package pl.project.pvpredictbackend.dto;

import lombok.Data;

import java.time.LocalDate;
/**
 * Data Transfer Object representing the daily forecast and generated energy information.
 */
@Data
public class DailyInformationDto {

    private LocalDate date;
    private int weatherCode;
    private double minTemperature;  // celsius
    private double maxTemperature;  // celsius
    private double generatedEnergy; // kWh
}
