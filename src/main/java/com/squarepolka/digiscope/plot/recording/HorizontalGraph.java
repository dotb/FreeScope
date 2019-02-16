package com.squarepolka.digiscope.plot.recording;

import java.math.BigDecimal;

public class HorizontalGraph {

    private BigDecimal maxVoltage;
    private BigDecimal minVolgate;
    private BigDecimal maxTime;

    public HorizontalGraph(BigDecimal maxVoltage, BigDecimal minVoltage, BigDecimal maxTime) {
        this.maxVoltage = maxVoltage;
        this.minVolgate = minVoltage;
        this.maxTime = maxTime;
    }

}
