package com.squarepolka.digiscope.plot.plotpoint;

import java.math.BigDecimal;

public class PlotPointRaw extends PlotPoint {

    private BigDecimal voltValue;
    private PlotPointPulse relatedPlotPointPulse;
    private PlotPointDigital relatedPlotPointDigital;

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

    public PlotPointPulse getRelatedPlotPointPulse() {
        return relatedPlotPointPulse;
    }

    public void setRelatedPlotPointPulse(PlotPointPulse relatedPlotPointPulse) {
        this.relatedPlotPointPulse = relatedPlotPointPulse;
    }

    public PlotPointDigital getRelatedPlotPointDigital() {
        return relatedPlotPointDigital;
    }

    public void setRelatedPlotPointDigital(PlotPointDigital relatedPlotPointDigital) {
        this.relatedPlotPointDigital = relatedPlotPointDigital;
    }

}
