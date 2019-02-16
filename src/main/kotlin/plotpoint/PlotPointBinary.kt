package plotpoint

import java.math.BigDecimal

class PlotPointBinary (val plotPointPulse: PlotPointPulse,
                       val digitLength: BigDecimal,
                       val digitalValue: Boolean): PlotPointPulse(plotPointPulse.getStartPulse(), plotPointPulse.getEndPulse()) {

    override fun toString(): String {
        return if (digitalValue) {
            super.toString() + "\tbinary 1 (length " + digitLength + ")"
        } else {
            super.toString() + "\tbinary 0 (length " + digitLength + ")"
        }
    }

}