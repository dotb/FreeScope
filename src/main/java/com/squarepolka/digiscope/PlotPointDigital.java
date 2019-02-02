package com.squarepolka.digiscope;

public class PlotPointDigital {

    private double timeValue;
    private boolean oneOrZero;

    public PlotPointDigital(double timeValue, boolean oneOrZero) {
        this.timeValue = timeValue;
        this.oneOrZero = oneOrZero;
    }

    public double getTimeValue() {
        return timeValue;
    }

    public boolean isOneOrZero() {
        return oneOrZero;
    }
}
