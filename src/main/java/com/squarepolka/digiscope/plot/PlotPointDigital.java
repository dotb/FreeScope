package com.squarepolka.digiscope.plot;

import java.math.BigDecimal;

public class PlotPointDigital {

    private BigDecimal timeValue;
    private boolean oneOrZero;

    public PlotPointDigital(BigDecimal timeValue, boolean oneOrZero) {
        this.timeValue = timeValue;
        this.oneOrZero = oneOrZero;
    }

    public BigDecimal getTimeValue() {
        return timeValue;
    }

    public boolean isOneOrZero() {
        return oneOrZero;
    }
}
