package com.example.rita_ola.simplebmi;

/**
 * Created by Rita_Ola on 14.03.2018.
 */

public class LbInBmiCounter extends BMI {

    private final static double minimalMass = 22;
    private final static double maximalMass = 550;
    private final static double minimalHeight = 20;
    private final static double maximalHeight = 100;

    public LbInBmiCounter(double mass, double height)
    {
        super(mass, height);
    }


    @Override
    protected boolean isMassValid() {
        return getMass() > minimalMass || getMass() < maximalMass;
    }

    @Override
    protected boolean isHeightValid() {
        return getHeight() > minimalHeight ||getHeight() < maximalHeight;
    }

    @Override
    public double calculateBMI() throws IllegalArgumentException {
        if(!isHeightValid() && !isMassValid()) {
            throw new IllegalArgumentException("Invalid data");
        }
        else
        {
            double bmi = this.mass / (this.height * this.height) * 703;
            return Math.round(bmi * 100f) / 100f;
        }
    }

}
