package com.squarepolka.digiscope.plot.plotpoint;


import java.math.BigDecimal;

public class PlotPointBinary extends PlotPointPulse {

    private boolean digitalValue;
    private BigDecimal digitLength;

    public PlotPointBinary(PlotPointPulse plotPointPulse, BigDecimal digitLength, boolean digitalValue) {
        super(plotPointPulse.getStartPulse(), plotPointPulse.getEndPulse());
        this.digitLength = digitLength;
        this.digitalValue = digitalValue;
    }

    public String toString() {
        if (digitalValue) {
            return super.toString() + "\tbinary 1 (length " + digitLength + ")";
        } else {
            return super.toString() + "\tbinary 0 (length " + digitLength + ")";
        }
    }

    public boolean isDigitalValue() {
        return digitalValue;
    }

}
