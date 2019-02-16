package com.squarepolka.digiscope.plot.processors;

import com.squarepolka.digiscope.plot.recording.PlotPointRecording;
import com.squarepolka.digiscope.plot.plotpoint.PlotPoint;

public interface ProcessorInterface {

    public PlotPoint processPlotPoint(PlotPoint plotPoint, PlotPointRecording plotPointRecording);

}
