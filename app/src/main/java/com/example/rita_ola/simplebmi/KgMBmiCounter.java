package com.example.rita_ola.simplebmi;

/**
 * Created by Rita_Ola on 14.03.2018.
 */

public class KgMBmiCounter extends BMI {

    private final static double minimalMass = 10;
    private final static double maximalMass = 250;
    private final static double minimalHeight = 0.5;
    private final static double maximalHeight = 2.5;

    public KgMBmiCounter(double m, double h)
    {
        super(m, h);
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
        if(!isHeightValid() && !isMassValid()){
            throw new IllegalArgumentException("Invalid data");
        }
        return mass/(height*height);
    }
}
