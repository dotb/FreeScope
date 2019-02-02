package com.squarepolka.digiscope;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlotPointRecording {

    private double timeResolution; // 20.0ms
    private double voltResolution; // 1v
    private double sampleCount; // 4064
    private List<PlotPointRaw> rawPoints;
    private List<PlotPointDigital> digitalPoints;

    public PlotPointRecording() {
        this.rawPoints = new ArrayList<PlotPointRaw>();
        this.digitalPoints = new ArrayList<PlotPointDigital>();
    }

    public void addPlotPoint(PlotPointRaw plotPointRaw, PlotPointDigital plotPointDigital) {
        rawPoints.add(plotPointRaw);
        digitalPoints.add(plotPointDigital);
    }

    public Iterable<PlotPointRaw> getRawPoints() {
        return rawPoints;
    }

}
