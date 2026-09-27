package net.paulem.pos.energy;

/**
 * Contains the energy api README's conversions from items to energy.
 */
public class EnergyReferences {
    public static int coalToEnergy(int coalAmount) {
        return coalAmount * 4000;
    }

    public static int plankToEnergy(int plankAmount) {
        return plankAmount * 750;
    }
}
