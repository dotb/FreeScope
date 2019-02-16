package com.squarepolka.digiscope.plot.processors;

import plotpoint.PlotPoint;
import recording.PlotPointRecording;

public interface ProcessorInterface {

    public PlotPoint processPlotPoint(PlotPoint plotPoint, PlotPointRecording plotPointRecording);

}
