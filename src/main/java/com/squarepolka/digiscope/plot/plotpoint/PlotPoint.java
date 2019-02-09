package com.squarepolka.digiscope.plot.plotpoint;

import java.math.BigDecimal;

public abstract class PlotPoint {
    private BigDecimal timestampMicroseconds;

    public PlotPoint(BigDecimal timestampMicroseconds) {
        this.timestampMicroseconds = timestampMicroseconds;
    }

    public BigDecimal getTimestampMicroseconds() {
        return timestampMicroseconds;
    }

    public BigDecimal getTimestampMilliseconds() {
        return timestampMicroseconds.multiply(new BigDecimal(1000));
    }

    public String toString() {
        return timestampMicroseconds.toString() + "us";
    }
}
