package recording

import plotpoint.PlotPoint

class PlotPointIterator (private var nextPlotPoint: PlotPoint?) : Iterator<PlotPoint> {

    override operator
    fun hasNext(): Boolean {
        return null != nextPlotPoint
    }

    override operator
    fun next(): PlotPoint {
        val plotPointToReturn = nextPlotPoint ?: PlotPoint()
        nextPlotPoint = nextPlotPoint?.nextPlotPoint
        return plotPointToReturn
    }

}

