package com.squarepolka.digiscope.plot.plotpoint;

import java.math.BigDecimal;

public class PlotPointRaw extends PlotPoint {

    private BigDecimal voltValue;

    public PlotPointRaw(BigDecimal timestampSeconds, BigDecimal voltValue) {
        super(timestampSeconds);
        this.voltValue = voltValue;
    }

    public BigDecimal getVoltValue() {
        return voltValue;
    }

    public String toString() {
        return super.toString() + "\t:\t" + voltValue + "v";
    }

}
