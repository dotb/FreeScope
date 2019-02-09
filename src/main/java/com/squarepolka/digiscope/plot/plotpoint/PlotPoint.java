package com.squarepolka.digiscope.plot.plotpoint;

import java.math.BigDecimal;

public abstract class PlotPoint {

    private PlotPoint previousPoint;
    private PlotPoint nextPlotPoint;
    private BigDecimal timestampSeconds;

    public PlotPoint(BigDecimal timestampSeconds) {
        this.previousPoint = null;
        this.nextPlotPoint = null;
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


    // Generated getters and setters
    public PlotPoint getPreviousPoint() {
        return previousPoint;
    }

    public void setPreviousPoint(PlotPoint previousPoint) {
        this.previousPoint = previousPoint;
    }

    public PlotPoint getNextPlotPoint() {
        return nextPlotPoint;
    }

    public void setNextPlotPoint(PlotPoint nextPlotPoint) {
        this.nextPlotPoint = nextPlotPoint;
    }
}
