package com.squarepolka.digiscope;

public class PlotPointRaw {
    private double timeValue;
    private double voltValue;

    public PlotPointRaw(double timeValue, double voltValue) {
        this.timeValue = timeValue;
        this.voltValue = voltValue;
    }

    public double getTimeValue() {
        return timeValue;
    }

    public double getVoltValue() {
        return voltValue;
    }
}
