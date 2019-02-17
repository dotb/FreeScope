package recording

import org.junit.Assert.*
import org.junit.Test
import plotpoint.PlotPoint

import java.math.BigDecimal

class PlotPointIteratorTest {

    @Test
    fun worksWithOneElement() {
        val firstPoint = PlotPoint(BigDecimal(1), BigDecimal(1))
        val subject = PlotPointIterator(firstPoint)
        assertEquals(true, subject.hasNext())
        assertEquals(firstPoint, subject.next())
        assertEquals(false, subject.hasNext()) // Should have no more
    }

    @Test
    fun worksWithTwoElements() {
        val firstPoint = PlotPoint(BigDecimal(1), BigDecimal(1))
        val secondPoint = PlotPoint(BigDecimal(2), BigDecimal(2))
        firstPoint.nextPlotPoint = secondPoint
        secondPoint.previousPoint = firstPoint

        val subject = PlotPointIterator(firstPoint)

        // Pop off the first element
        assertEquals(true, subject.hasNext())
        assertEquals(firstPoint, subject.next())

        // Pop off the second element
        assertEquals(true, subject.hasNext())
        assertEquals(secondPoint, subject.next())
        assertEquals(false, subject.hasNext()) // Should have no more
    }

    @Test
    fun worksWithThreeElements() {
        val firstPoint = PlotPoint(BigDecimal(1), BigDecimal(1))
        val secondPoint = PlotPoint(BigDecimal(2), BigDecimal(2))
        val thirdPoint = PlotPoint(BigDecimal(3), BigDecimal(3))
        firstPoint.nextPlotPoint = secondPoint
        secondPoint.nextPlotPoint = thirdPoint
        thirdPoint.previousPoint = secondPoint
        secondPoint.previousPoint = firstPoint

        val subject = PlotPointIterator(firstPoint)

        // Pop off the first element
        assertEquals(true, subject.hasNext())
        assertEquals(firstPoint, subject.next())

        // Pop off the second element
        assertEquals(true, subject.hasNext())
        assertEquals(secondPoint, subject.next())

        // Pop off the third element
        assertEquals(true, subject.hasNext())
        assertEquals(thirdPoint, subject.next())
        assertEquals(false, subject.hasNext()) // Should have no more
    }

}

