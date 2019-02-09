package com.squarepolka.digiscope.plot.plotpoint;

import java.math.BigDecimal;

public class PlotPointPulse extends PlotPointRaw {

    private PlotPointRaw startPulse;
    private PlotPointRaw endPulse;
    private BigDecimal durationSeconds;

    public PlotPointPulse(PlotPointRaw startPulse, PlotPointRaw endPulse) {
        super(startPulse.getTimestampSeconds(), startPulse.getVoltValue());
        this.startPulse = startPulse;
        this.endPulse = endPulse;
        this.durationSeconds = endPulse.getTimestampSeconds().subtract(startPulse.getTimestampSeconds());
    }


    public BigDecimal getDurationSeconds() {
        return durationSeconds;
    }

    public BigDecimal getDurationMilliseconds() {
        return durationSeconds.multiply(new BigDecimal(1000));
    }

    public BigDecimal getDurationMicroseconds() {
        return durationSeconds.multiply(new BigDecimal(1000000));
    }

    public BigDecimal getDurationNanoseconds() {
        return durationSeconds.multiply(new BigDecimal(1000000000));
    }

    public String toString() {

        BigDecimal valueOfOne = new BigDecimal(1);

        if (getDurationSeconds().compareTo(valueOfOne) >= 0) {
            return super.toString() + "\tfor " + getDurationSeconds() + "s";
        } else if (getDurationMilliseconds().compareTo(valueOfOne) >= 0) {
            return super.toString() + "\tfor " + getDurationMilliseconds() + "ms";
        } else if (getDurationMicroseconds().compareTo(valueOfOne) >= 0) {
            return super.toString() + "\tfor " + getDurationMicroseconds() + "us";
        } else {
            return super.toString() + "\tfor " + getDurationSeconds() + "us";
        }

    }

}
