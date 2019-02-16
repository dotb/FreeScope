package graph

import java.math.BigDecimal

class HorizontalGraph (maxVoltage: BigDecimal, minVoltage: BigDecimal, maxTime: BigDecimal) {

    private val maxVoltage: BigDecimal
    private val minVoltage: BigDecimal
    private val maxTime: BigDecimal

    init {
        this.maxVoltage = maxVoltage
        this.minVoltage = minVoltage
        this.maxTime = maxTime
    }

    fun test() {

        println("hello ")
    }
}