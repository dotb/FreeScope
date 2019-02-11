package com.squarepolka.digiscope.plot.processors;

import com.squarepolka.digiscope.plot.PlotPointRecording;
import com.squarepolka.digiscope.plot.plotpoint.PlotPoint;
import com.squarepolka.digiscope.plot.plotpoint.PlotPointBinary;
import com.squarepolka.digiscope.plot.plotpoint.PlotPointPulse;

import java.math.BigDecimal;

public class BinaryProcessor {

    private PlotPointPulse lastPulsePoint;

    public PlotPoint processPlotPoint(PlotPoint plotPoint, PlotPointRecording plotPointRecording) {
        /*
        Time how long the base logic level is maintained and add a 1 or a 0 for longs and shorts.
        We need to add the binary point at the beginning of the pulse, for consistency, so it
        makes sense to align each binary point with the end of the pulse point, or the start of the
        binary pulse. I.e. pulseEnd.nextPlotPoint();
         */

        if (plotPoint instanceof PlotPointPulse) {
            PlotPointPulse currentPulse = (PlotPointPulse) plotPoint;
            if (null == lastPulsePoint) {
                // This is the first pulse that we're processing.
                lastPulsePoint = currentPulse;
            } else {
                // Measure the length of the digit, create a binary plot object, and start measuring the next digit.
                BigDecimal digitLength = currentPulse.getTimestampSeconds().subtract(lastPulsePoint.getTimestampSeconds());
                PlotPointBinary plotPointBinary = null;
                if (digitLength.compareTo(new BigDecimal(0.00094)) >= 0) { // is the length greater than 940us
                    plotPointBinary = new PlotPointBinary(lastPulsePoint, digitLength, true);
                } else {
                    plotPointBinary = new PlotPointBinary(lastPulsePoint, digitLength, false);
                }
                plotPointRecording.replacePlotPoint(lastPulsePoint, plotPointBinary);
                lastPulsePoint = currentPulse;
                return plotPointBinary;
            }
        }
        return plotPoint;
    }

}
