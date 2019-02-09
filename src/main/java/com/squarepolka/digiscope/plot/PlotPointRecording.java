package com.squarepolka.digiscope.plot;

import com.squarepolka.digiscope.plot.plotpoint.PlotPoint;

public class PlotPointRecording {

    private double timeResolution; // 20.0ms
    private double voltResolution; // 1v
    private double sampleCount; // 4064
    private PlotPoint firstPlotPoint; // The start of a linked list of points
    private PlotPoint lastPlotPoint; // The end of the list of points
    private double count;

    public PlotPointRecording() {
        this.firstPlotPoint = null;
        this.count = 0;
    }

    public void addPlotPoint(PlotPoint newPlotPoint) {
        if (null == firstPlotPoint) { // This is the first plot point
            firstPlotPoint = newPlotPoint;
            lastPlotPoint = newPlotPoint;
        } else { // Add this point to the existing list
            lastPlotPoint.setNextPlotPoint(newPlotPoint);
            newPlotPoint.setPreviousPoint(lastPlotPoint);
            lastPlotPoint = newPlotPoint;
        }
        count++;
    }

    public void replacePlotPoint(PlotPoint originalPoint, PlotPoint newPlotPoint) {
        if (null == firstPlotPoint) { // The recoding is empty. Start fresh
            addPlotPoint(newPlotPoint);
        } else {
            PlotPoint leftHandPoint = originalPoint.getPreviousPoint();
            PlotPoint rightHandPlotPoint = originalPoint.getNextPlotPoint();
            if (null != leftHandPoint) {
                leftHandPoint.setNextPlotPoint(newPlotPoint);
            }
            if (null != rightHandPlotPoint) {
                rightHandPlotPoint.setPreviousPoint(newPlotPoint);
            }

            newPlotPoint.setPreviousPoint(leftHandPoint);
            newPlotPoint.setNextPlotPoint(rightHandPlotPoint);
            originalPoint.setPreviousPoint(null);
            originalPoint.setNextPlotPoint(null);

            // replace the first plot point if required
            if (originalPoint == firstPlotPoint) {
                firstPlotPoint = newPlotPoint;
            }
        }
    }

    public PlotPointIterator getPoints() {
        return new PlotPointIterator(firstPlotPoint);
    }

    public String getVerticalGraph() {
        PlotPointIterator plotPointIterator = getPoints();
        StringBuffer stringBuffer = new StringBuffer();
        int graphIndex = 0;
        while (plotPointIterator.hasNext()) {
            PlotPoint plotPoint = plotPointIterator.next();
            stringBuffer.append(graphIndex);
            stringBuffer.append("\t");
            stringBuffer.append(plotPoint.toString());
            stringBuffer.append("\n");
            graphIndex++;
        }
        return stringBuffer.toString();
    }

    // Generated getters and setters
    public double getCount() {
        return count;
    }
}
