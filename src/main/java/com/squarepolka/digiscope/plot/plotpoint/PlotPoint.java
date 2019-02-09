package com.squarepolka.digiscope.plot.plotpoint;

import java.math.BigDecimal;

public abstract class PlotPoint {
    private BigDecimal timestampSeconds;

    public PlotPoint(BigDecimal timestampSeconds) {
        this.timestampSeconds = timestampSeconds;
    }

    public BigDecimal getTimestampSeconds() {
        return timestampSeconds;
    }

    public BigDecimal getTimestampMilliseconds() {
        return timestampSeconds.multiply(new BigDecimal(1000));
    }

    public String toString() {
        return getTimestampMilliseconds() + "ms";
    }
}
