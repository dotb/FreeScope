package recording

import plotpoint.PlotPoint
import plotpoint.PlotPointBinary
import java.math.BigDecimal

class PlotPointRecording {

    // Keep pointers to different ends of the plot points so that the linked list can be traversed in different ways
    private var firstPlotPoint: PlotPoint? = null
    private var lastPlotPoint: PlotPoint? = null
    private var largestPlotPoint: PlotPoint? = null
    var count: Int = 0

    fun addPlotPoint(newPlotPoint: PlotPoint) {
        newPlotPoint.index = count
        if (null == firstPlotPoint) { // This is the first plot point
            firstPlotPoint = newPlotPoint
            lastPlotPoint = newPlotPoint
            largestPlotPoint = newPlotPoint
        } else { // Add this point to the existing list
            lastPlotPoint?.nextPlotPoint = newPlotPoint
            newPlotPoint.previousPoint = lastPlotPoint
            lastPlotPoint = newPlotPoint

            insertPointIntoSizedList(newPlotPoint)
        }
        count++
    }

    // Run along the points, largest to smallest, to find the correct position for the new point
    fun addPointToSizedList(newPlotPoint: PlotPoint) {
        if (null == largestPlotPoint) { // First point in the list
            largestPlotPoint = newPlotPoint
        } else {
            insertPointIntoSizedList(newPlotPoint)
            // Update the largestPlotPoint reference if required
            if (newPlotPoint.voltValue.compareTo(largestPlotPoint?.voltValue) >= 1) {
                largestPlotPoint = newPlotPoint
            }
        }
    }

    fun insertPointIntoSizedList(newPlotPoint: PlotPoint) : Boolean {
        var comparisonPoint = largestPlotPoint
        while (null != comparisonPoint) {
            if (newPlotPoint.voltValue.compareTo(comparisonPoint.voltValue) >= 1) {
                addPointBeforeSmallerPoint(comparisonPoint, newPlotPoint)
                return true
            } else if (newPlotPoint.voltValue.compareTo(comparisonPoint.voltValue) == 0) {
                addPointAfterLargerPoint(comparisonPoint, newPlotPoint)
                return true
            }
            val nextComparisonPoint = comparisonPoint.nextSmallerPoint
            if (null == nextComparisonPoint) {
                // This must be the smallest point, add it to the end of the list
                addPointAfterLargerPoint(comparisonPoint, newPlotPoint)
                return true
            }
            comparisonPoint = nextComparisonPoint
        }
        return false
    }

    fun addPointBeforeSmallerPoint(existingSmallPlotPoint: PlotPoint, newPlotPoint: PlotPoint) {
        val existingLargePlotPoint = existingSmallPlotPoint.previousLargerPoint
        newPlotPoint.nextSmallerPoint = existingSmallPlotPoint
        newPlotPoint.previousLargerPoint = existingLargePlotPoint
        existingLargePlotPoint?.nextSmallerPoint = newPlotPoint
        existingSmallPlotPoint.previousLargerPoint = newPlotPoint
    }

    fun addPointAfterLargerPoint(existingLargePoint: PlotPoint, newPlotPoint: PlotPoint) {
        val existingSmallPoint = existingLargePoint.nextSmallerPoint
        newPlotPoint.previousLargerPoint = existingLargePoint
        newPlotPoint.nextSmallerPoint = existingSmallPoint
        existingLargePoint.nextSmallerPoint = newPlotPoint
        existingSmallPoint?.previousLargerPoint = newPlotPoint
    }

    fun replacePlotPoint(originalPoint: PlotPoint, newPlotPoint: PlotPoint) {
        if (null == firstPlotPoint) { // The recoding is empty. Start fresh
            addPlotPoint(newPlotPoint)
        } else {
            val leftHandPoint = originalPoint.previousPoint
            val rightHandPlotPoint = originalPoint.nextPlotPoint
            if (null != leftHandPoint) {
                leftHandPoint.nextPlotPoint = newPlotPoint
            }
            if (null != rightHandPlotPoint) {
                rightHandPlotPoint.previousPoint = newPlotPoint
            }

            newPlotPoint.previousPoint = leftHandPoint
            newPlotPoint.nextPlotPoint = rightHandPlotPoint
            originalPoint.previousPoint = null
            originalPoint.nextPlotPoint = null

            // replace the first plot point if required
            if (originalPoint === firstPlotPoint) {
                firstPlotPoint = newPlotPoint
            }
        }
    }

    fun getPoints(): PlotPointIterator {
        return PlotPointIterator(firstPlotPoint)
    }

    fun getPointsLargeToSmall(): PlotPointIterator {
        return PlotPointIterator(largestPlotPoint)
    }

    fun getPlotPoints(): String {
        val plotPointIterator = getPoints()
        val stringBuffer = StringBuffer()
        var graphIndex = 0
        while (plotPointIterator.hasNext()) {
            val plotPoint = plotPointIterator.next()
            stringBuffer.append(graphIndex)
            stringBuffer.append("\t")
            stringBuffer.append(plotPoint.toString())
            stringBuffer.append("\n")
            graphIndex++
        }
        return stringBuffer.toString()
    }

    fun getBinaryDigits(): String {
        val plotPointIterator = getPoints()
        val stringBuffer = StringBuffer()

        while (plotPointIterator.hasNext()) {
            val plotPoint = plotPointIterator.next()
            if (plotPoint is PlotPointBinary) {
                if (plotPoint.digitalValue) {
                    stringBuffer.append("1")
                } else {
                    stringBuffer.append("0")
                }
            }
        }
        return stringBuffer.toString()
    }

    fun printHorizontalGraph() {
        println("Graph:")
        var nextPlotPoint = largestPlotPoint
        while (null != nextPlotPoint) {

            /*
            for (i in 0..nextPlotPoint.index) {
                print("")
            }
            if (nextPlotPoint.voltValue.compareTo(nextPlotPoint.nextSmallerPoint?.voltValue) == 0) {
                print("*")
            } else {
                println("*")
            }*/
            println(nextPlotPoint)
            nextPlotPoint = nextPlotPoint.nextSmallerPoint
        }
    }

}