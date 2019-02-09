package com.squarepolka.digiscope.plot.plotpoint;

import java.math.BigDecimal;

public class PlotPointPulse extends PlotPointRaw {

    private PlotPointRaw startPulse;
    private PlotPointRaw endPulse;
    private BigDecimal duration;

    public PlotPointPulse(PlotPointRaw startPulse, PlotPointRaw endPulse) {
        super(startPulse.getTimestampMicroseconds(), startPulse.getVoltValue());
        this.startPulse = startPulse;
        this.endPulse = endPulse;
        this.duration = endPulse.getTimestampMicroseconds().subtract(startPulse.getTimestampMicroseconds());
    }

    public String toString() {
        return startPulse.getTimestampMicroseconds() + "us : " + startPulse.getVoltValue() + "v for " + duration + "us";
    }

}
