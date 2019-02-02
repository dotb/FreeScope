package com.squarepolka.digiscope.plot;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlotPointRaw {
    private BigDecimal timestampMicroseconds;
    private BigDecimal voltValue;

    public PlotPointRaw(BigDecimal timestampMicroseconds, BigDecimal voltValue) {
        this.timestampMicroseconds = timestampMicroseconds;
        this.voltValue = voltValue;
    }

    public BigDecimal getTimestampMicroseconds() {
        return timestampMicroseconds;
    }

    public BigDecimal getTimestampMilliseconds() {
        return timestampMicroseconds.multiply(new BigDecimal(1000));
    }

    public BigDecimal getVoltValue() {
        return voltValue;
    }
}
