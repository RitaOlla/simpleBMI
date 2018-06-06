package com.example.rita_ola.simplebmi;

/**
 * Created by Rita_Ola on 15.03.2018.
 */

public abstract class BMI {
    protected double mass;
    protected double height;
    private static final double minBmi = 18.5;
    private static final double maxBmi = 25;


    public BMI(double m, double h) {
        mass = m;
        height = h;
    }


    public double getMass()
    {
        return mass;
    }
    public void setMass(double mass)
    {
        this.mass = mass;
    }

    public double getHeight()
    {
        return height;
    }
    public void setHeight(double height)
    {
        this.height = height;
    }

    abstract protected boolean isMassValid();
    abstract protected boolean isHeightValid();
    abstract public double calculateBMI();

}

