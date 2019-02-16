package com.squarepolka.digiscope.plot.processors;

import plotpoint.PlotPoint;
import plotpoint.PlotPointPulse;
import recording.PlotPointRecording;

import java.math.BigDecimal;

public class PulseProcessor {

    private PlotPoint baseLinePoint = null;
    private PlotPoint pulseStartPoint = null;
    private PlotPoint pulseEndPoint = null;

    public PlotPoint processPlotPoint(PlotPoint plotPoint, PlotPointRecording plotPointRecording) {
        // Set the baseline point from which pules will be measured
        if (null == baseLinePoint) {
            baseLinePoint = plotPoint;
        }

        // Does this plot point belong to the baseline or a pulse?
        if (isPointAtBaseline(plotPoint, baseLinePoint)) { // Belongs to the baseline
            // We're back to the baseline. Did we ever leave? Have we been recording a pulse?
            if (null != pulseStartPoint && null != pulseEndPoint) {
                // Yes, we've been recording a pulse!
                PlotPointPulse plotPointPulse = new PlotPointPulse(pulseStartPoint, pulseEndPoint);
                plotPointRecording.replacePlotPoint(pulseStartPoint, plotPointPulse);
                // Reset the pointers so that they can record the next pulse
                pulseStartPoint = pulseEndPoint = null;
                return plotPointPulse;
            }
        } else { // Belongs to a pulse
            if (null == pulseStartPoint) { // There is no starting point so we begin recording a pulse
                pulseStartPoint = plotPoint;
            }
            // Keep pointing to the potential end of the pulse
            pulseEndPoint = plotPoint;
        }
        return plotPoint;
    }

    public boolean isPointAtBaseline(PlotPoint plotPoint, PlotPoint baseLinePlotPoint) {
        BigDecimal threshold = new BigDecimal(0.8);
        BigDecimal difference =  plotPoint.getVoltValue().subtract(baseLinePlotPoint.getVoltValue());
        return difference.abs().compareTo(threshold) <= 0;
    }

}
