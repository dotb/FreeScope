package com.squarepolka.digiscope.plot;

import com.squarepolka.digiscope.plot.plotpoint.PlotPoint;

import java.util.Iterator;

public class PlotPointIterator implements Iterator<PlotPoint> {

    private PlotPoint nextPlotPoint;

    public PlotPointIterator(PlotPoint firstPlotPoint) {
        this.nextPlotPoint = firstPlotPoint;
    }

    public boolean hasNext() {
        return null != nextPlotPoint;
    }

    public PlotPoint next() {
        PlotPoint plotPointToReturn = nextPlotPoint;
        nextPlotPoint = nextPlotPoint.getNextPlotPoint();
        return plotPointToReturn;
    }

}
