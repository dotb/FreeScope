package plotpoint;

import java.math.BigDecimal

open class PlotPoint(val timestampSeconds: BigDecimal = BigDecimal(0), val voltValue: BigDecimal = BigDecimal(0)) {

    var previousPoint: PlotPoint? = null
    var nextPlotPoint: PlotPoint? = null
    var previousLargerPoint: PlotPoint? = null
    var nextSmallerPoint: PlotPoint? = null
    var index: Int = 0
    
    fun getTimestampMilliseconds(): BigDecimal {
        return timestampSeconds.multiply(BigDecimal(1000))
    }

    override
    fun toString(): String {
        val timestampMilliseconds = getTimestampMilliseconds()
        return "${index} : ${timestampMilliseconds}ms : ${voltValue}v"
    }

}
