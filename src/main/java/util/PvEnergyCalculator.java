package util;

/**
 * Utility class for calculating generated solar energy based on installation parameters.
 */
public class PvEnergyCalculator {

    private static final double PV_POWER_KW = 2.5;
    private static final double PANEL_EFFICIENCY = 0.2;

    /**
     * Calculates generated energy in kilowatt-hours (kWh).
     *
     * @param sunshineHours number of sunshine hours
     * @return generated energy in kWh
     */
    public static double calculateGeneratedEnergy(double sunshineHours) {
        return PV_POWER_KW * sunshineHours * PANEL_EFFICIENCY;
    }
}
