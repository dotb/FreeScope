package plotpoint

import java.math.BigDecimal

open class PlotPointPulse(private val startPulse: PlotPoint,
                          private val endPulse: PlotPoint): PlotPoint(startPulse.timestampSeconds, startPulse.voltValue) {

    private val durationSeconds: BigDecimal = endPulse.timestampSeconds.subtract(startPulse.timestampSeconds)

    fun getDurationMilliseconds(): BigDecimal {
        return durationSeconds.multiply(BigDecimal(1000))
    }

    fun getDurationMicroseconds(): BigDecimal {
        return durationSeconds.multiply(BigDecimal(1000000))
    }

    fun getDurationNanoseconds(): BigDecimal {
        return durationSeconds.multiply(BigDecimal(1000000000))
    }

    override fun toString(): String {
        val valueOfOne = BigDecimal(1)
        return if (timestampSeconds.compareTo(valueOfOne) >= 0) {
            super.toString() + "\tpulse for " + timestampSeconds + "s"
        } else if (getDurationMilliseconds().compareTo(valueOfOne) >= 0) {
            super.toString() + "\tpulse for " + getDurationMilliseconds() + "ms"
        } else if (getDurationMicroseconds().compareTo(valueOfOne) >= 0) {
            super.toString() + "\tpulse for " + getDurationMicroseconds() + "us"
        } else {
            super.toString() + "\tpulse for " + timestampSeconds + "us"
        }
    }

    // Generated getters and setters
    fun getStartPulse(): PlotPoint {
        return startPulse
    }

    fun getEndPulse(): PlotPoint {
        return endPulse
    }
}