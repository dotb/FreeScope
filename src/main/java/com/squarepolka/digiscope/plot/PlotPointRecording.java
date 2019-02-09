package com.squarepolka.digiscope.plot;

import com.squarepolka.digiscope.plot.plotpoint.PlotPointDigital;
import com.squarepolka.digiscope.plot.plotpoint.PlotPointPulse;
import com.squarepolka.digiscope.plot.plotpoint.PlotPointRaw;

import java.util.ArrayList;
import java.util.List;

public class PlotPointRecording {

    private double timeResolution; // 20.0ms
    private double voltResolution; // 1v
    private double sampleCount; // 4064
    private List<PlotPointRaw> rawPoints;
    private List<PlotPointDigital> digitalPoints;
    private List<PlotPointPulse> pulsePoints;

    public PlotPointRecording() {
        this.rawPoints = new ArrayList<PlotPointRaw>();
        this.pulsePoints = new ArrayList<PlotPointPulse>();
        this.digitalPoints = new ArrayList<PlotPointDigital>();
    }

    public void addPlotPointRaw(PlotPointRaw plotPointRaw) {
        rawPoints.add(plotPointRaw);
    }

    public void addPlotPointPulse(PlotPointPulse plotPointPulse) {
        pulsePoints.add(plotPointPulse);
    }

    public Iterable<PlotPointPulse> getPulsePoints() {
        return pulsePoints;
    }

    public Iterable<PlotPointRaw> getRawPoints() {
        return rawPoints;
    }

}
