package recording

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import plotpoint.PlotPoint
import java.math.BigDecimal

@RunWith(PowerMockRunner::class)
@PrepareForTest(PlotPointRecording::class)
class PlotPointRecordingTest {

    @InjectMocks
    internal var subject: PlotPointRecording? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addSinglePlotPoint() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        assertEquals(0f, subject!!.count.toFloat(), 0f)
        subject!!.addPlotPoint(firstPlotPoint)
        assertEquals(true, subject!!.getPoints().hasNext())
        assertEquals(1f, subject!!.count.toFloat(), 0f)
        val nextPlotPoint = subject!!.getPoints().next()
        assertEquals(firstPlotPoint, nextPlotPoint)
    }

    @Test
    fun addTwoPlotPoints() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        val secondPlotPoint = PlotPoint(BigDecimal(3), BigDecimal(4))

        subject!!.addPlotPoint(firstPlotPoint)
        subject!!.addPlotPoint(secondPlotPoint)
        assertEquals(true, subject!!.getPoints().hasNext())
        assertEquals(2f, subject!!.count.toFloat(), 0f)
        val iterator = subject!!.getPoints()
        assertEquals(firstPlotPoint, iterator.next())
        assertEquals(secondPlotPoint, iterator.next())
    }

    @Test
    fun addThreePlotPoints() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        val secondPlotPoint = PlotPoint(BigDecimal(3), BigDecimal(4))
        val thirdPlotPoint = PlotPoint(BigDecimal(5), BigDecimal(6))

        subject!!.addPlotPoint(firstPlotPoint)
        subject!!.addPlotPoint(secondPlotPoint)
        subject!!.addPlotPoint(thirdPlotPoint)
        assertEquals(true, subject!!.getPoints().hasNext())
        assertEquals(3f, subject!!.count.toFloat(), 0f)
        val iterator = subject!!.getPoints()
        assertEquals(firstPlotPoint, iterator.next())
        assertEquals(secondPlotPoint, iterator.next())
        assertEquals(thirdPlotPoint, iterator.next())
    }

    @Test
    fun replaceSinglePlotPoint() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        val replacementPlotPoint = PlotPoint(BigDecimal(9), BigDecimal(9))

        subject!!.addPlotPoint(firstPlotPoint)
        assertEquals("The recording count starts correctly", 1f, subject!!.count.toFloat(), 0f)
        subject!!.replacePlotPoint(firstPlotPoint, replacementPlotPoint)
        assertEquals("The recording remains the same", 1f, subject!!.count.toFloat(), 0f)
        assertEquals(replacementPlotPoint, subject!!.getPoints().next())
    }

    @Test
    fun replaceFirstOfTwoPlotPoints() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        val secondPlotPoint = PlotPoint(BigDecimal(3), BigDecimal(4))
        val replacementPlotPoint = PlotPoint(BigDecimal(9), BigDecimal(9))

        subject!!.addPlotPoint(firstPlotPoint)
        subject!!.addPlotPoint(secondPlotPoint)
        assertEquals("The recording count starts correctly", 2f, subject!!.count.toFloat(), 0f)
        subject!!.replacePlotPoint(firstPlotPoint, replacementPlotPoint)
        assertEquals("The recording remains the same", 2f, subject!!.count.toFloat(), 0f)

        val iterator = subject!!.getPoints()
        assertEquals(replacementPlotPoint, iterator.next())
        assertEquals(secondPlotPoint, iterator.next())
    }

    @Test
    fun replaceSecondOfTwoPlotPoints() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        val secondPlotPoint = PlotPoint(BigDecimal(3), BigDecimal(4))
        val replacementPlotPoint = PlotPoint(BigDecimal(9), BigDecimal(9))

        subject!!.addPlotPoint(firstPlotPoint)
        subject!!.addPlotPoint(secondPlotPoint)
        assertEquals("The recording count starts correctly", 2f, subject!!.count.toFloat(), 0f)
        subject!!.replacePlotPoint(secondPlotPoint, replacementPlotPoint)
        assertEquals("The recording remains the same", 2f, subject!!.count.toFloat(), 0f)

        val iterator = subject!!.getPoints()
        assertEquals(firstPlotPoint, iterator.next())
        assertEquals(replacementPlotPoint, iterator.next())
    }

    @Test
    fun replaceFirstOfThreePlotPoints() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        val secondPlotPoint = PlotPoint(BigDecimal(3), BigDecimal(4))
        val thirdPlotPoint = PlotPoint(BigDecimal(5), BigDecimal(6))
        val replacementPlotPoint = PlotPoint(BigDecimal(9), BigDecimal(9))

        subject!!.addPlotPoint(firstPlotPoint)
        subject!!.addPlotPoint(secondPlotPoint)
        subject!!.addPlotPoint(thirdPlotPoint)
        assertEquals("The recording count starts correctly", 3f, subject!!.count.toFloat(), 0f)
        subject!!.replacePlotPoint(firstPlotPoint, replacementPlotPoint)
        assertEquals("The recording remains the same", 3f, subject!!.count.toFloat(), 0f)

        val iterator = subject!!.getPoints()
        assertEquals(replacementPlotPoint, iterator.next())
        assertEquals(secondPlotPoint, iterator.next())
        assertEquals(thirdPlotPoint, iterator.next())
    }

    @Test
    fun replaceSecondOfThreePlotPoints() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        val secondPlotPoint = PlotPoint(BigDecimal(3), BigDecimal(4))
        val thirdPlotPoint = PlotPoint(BigDecimal(5), BigDecimal(6))
        val replacementPlotPoint = PlotPoint(BigDecimal(9), BigDecimal(9))

        subject!!.addPlotPoint(firstPlotPoint)
        subject!!.addPlotPoint(secondPlotPoint)
        subject!!.addPlotPoint(thirdPlotPoint)
        assertEquals("The recording count starts correctly", 3f, subject!!.count.toFloat(), 0f)
        subject!!.replacePlotPoint(secondPlotPoint, replacementPlotPoint)
        assertEquals("The recording remains the same", 3f, subject!!.count.toFloat(), 0f)

        val iterator = subject!!.getPoints()
        assertEquals(firstPlotPoint, iterator.next())
        assertEquals(replacementPlotPoint, iterator.next())
        assertEquals(thirdPlotPoint, iterator.next())
    }

    @Test
    fun replaceThirdOfThreePlotPoints() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        val secondPlotPoint = PlotPoint(BigDecimal(3), BigDecimal(4))
        val thirdPlotPoint = PlotPoint(BigDecimal(5), BigDecimal(6))
        val replacementPlotPoint = PlotPoint(BigDecimal(9), BigDecimal(9))

        subject!!.addPlotPoint(firstPlotPoint)
        subject!!.addPlotPoint(secondPlotPoint)
        subject!!.addPlotPoint(thirdPlotPoint)
        assertEquals("The recording count starts correctly", 3f, subject!!.count.toFloat(), 0f)
        subject!!.replacePlotPoint(thirdPlotPoint, replacementPlotPoint)
        assertEquals("The recording remains the same", 3f, subject!!.count.toFloat(), 0f)

        val iterator = subject!!.getPoints()
        assertEquals(firstPlotPoint, iterator.next())
        assertEquals(secondPlotPoint, iterator.next())
        assertEquals(replacementPlotPoint, iterator.next())
    }

    @Test
    fun testVerticalGraphOutput() {
        val firstPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(2))
        val secondPlotPoint = PlotPoint(BigDecimal(3), BigDecimal(4))
        val thirdPlotPoint = PlotPoint(BigDecimal(5), BigDecimal(6))

        subject!!.addPlotPoint(firstPlotPoint)
        subject!!.addPlotPoint(secondPlotPoint)
        subject!!.addPlotPoint(thirdPlotPoint)

        val verticalGraphExpected = "0\t0 : 1000ms : 2v\n" +
                "1\t1 : 3000ms : 4v\n" +
                "2\t2 : 5000ms : 6v\n"
        val verticalGraphGenerated = subject!!.getPlotPoints()
        assertEquals(verticalGraphExpected, verticalGraphGenerated)
    }

    @Test
    fun addPointBetweenLargerSmallerPointsUsingTheSmalerPointAsAReference() {
        val largePlotPoint = PlotPoint(BigDecimal(3), BigDecimal(3))
        val middleNewPlotPoint = PlotPoint(BigDecimal(2), BigDecimal(2))
        val smallPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(1))

        largePlotPoint.nextSmallerPoint = smallPlotPoint
        smallPlotPoint.previousLargerPoint = largePlotPoint
        subject!!.addPointBeforeSmallerPoint(smallPlotPoint, middleNewPlotPoint)

        assertEquals(null, largePlotPoint.previousLargerPoint)
        assertEquals(middleNewPlotPoint, largePlotPoint.nextSmallerPoint)
        assertEquals(largePlotPoint, middleNewPlotPoint.previousLargerPoint)
        assertEquals(smallPlotPoint, middleNewPlotPoint.nextSmallerPoint)
        assertEquals(middleNewPlotPoint, smallPlotPoint.previousLargerPoint)
        assertEquals(null, smallPlotPoint.nextSmallerPoint)
    }

    @Test
    fun addPointBetweenLargerSmallerPointUsingTheLargerPointAsAReference() {
        val largePlotPoint = PlotPoint(BigDecimal(3), BigDecimal(3))
        val middleNewPlotPoint = PlotPoint(BigDecimal(2), BigDecimal(2))
        val smallPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(1))

        largePlotPoint.nextSmallerPoint = smallPlotPoint
        smallPlotPoint.previousLargerPoint = largePlotPoint
        subject!!.addPointAfterLargerPoint(largePlotPoint, middleNewPlotPoint)

        assertEquals(null, largePlotPoint.previousLargerPoint)
        assertEquals(middleNewPlotPoint, largePlotPoint.nextSmallerPoint)
        assertEquals(largePlotPoint, middleNewPlotPoint.previousLargerPoint)
        assertEquals(smallPlotPoint, middleNewPlotPoint.nextSmallerPoint)
        assertEquals(middleNewPlotPoint, smallPlotPoint.previousLargerPoint)
        assertEquals(null, smallPlotPoint.nextSmallerPoint)
    }

    @Test
    fun addPointAfterSingleLargerPoint() {
        val largePlotPoint = PlotPoint(BigDecimal(3), BigDecimal(3))
        val middleNewPlotPoint = PlotPoint(BigDecimal(2), BigDecimal(2))

        subject!!.addPointAfterLargerPoint(largePlotPoint, middleNewPlotPoint)
        assertEquals(null, largePlotPoint.previousLargerPoint)
        assertEquals(middleNewPlotPoint, largePlotPoint.nextSmallerPoint)
        assertEquals(largePlotPoint, middleNewPlotPoint.previousLargerPoint)
        assertEquals(null, middleNewPlotPoint.nextSmallerPoint)
    }

    @Test
    fun addPointBehindSingleSmallerPoint() {
        val middleNewPlotPoint = PlotPoint(BigDecimal(2), BigDecimal(2))
        val smallPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(1))

        subject!!.addPointBeforeSmallerPoint(smallPlotPoint, middleNewPlotPoint)
        assertEquals(null, middleNewPlotPoint.previousLargerPoint)
        assertEquals(smallPlotPoint, middleNewPlotPoint.nextSmallerPoint)
        assertEquals(middleNewPlotPoint, smallPlotPoint.previousLargerPoint)
        assertEquals(null, smallPlotPoint.nextSmallerPoint)
    }

    @Test
    fun addPointToSizedListFromLargeToSmall() {
        val largePlotPoint = PlotPoint(BigDecimal(3), BigDecimal(3))
        val middleNewPlotPoint = PlotPoint(BigDecimal(2), BigDecimal(2))
        val smallPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(1))

        subject!!.addPointToSizedList(largePlotPoint)
        subject!!.addPointToSizedList(middleNewPlotPoint)
        subject!!.addPointToSizedList(smallPlotPoint)

        val iterator = subject!!.getPointsLargeToSmall()
        assertEquals(largePlotPoint, iterator.next())
        assertEquals(middleNewPlotPoint, iterator.next())
        assertEquals(smallPlotPoint, iterator.next())
    }

    @Test
    fun addPointToSizedListFromSmallToLarge() {
        val largePlotPoint = PlotPoint(BigDecimal(3), BigDecimal(3))
        val middleNewPlotPoint = PlotPoint(BigDecimal(2), BigDecimal(2))
        val smallPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(1))

        subject!!.addPointToSizedList(smallPlotPoint)
        subject!!.addPointToSizedList(middleNewPlotPoint)
        subject!!.addPointToSizedList(largePlotPoint)

        val iterator = subject!!.getPointsLargeToSmall()
        assertEquals(largePlotPoint, iterator.next())
        assertEquals(middleNewPlotPoint, iterator.next())
        assertEquals(smallPlotPoint, iterator.next())
    }

    @Test
    fun addPointToSizedListStartingWithMiddle() {
        val largePlotPoint = PlotPoint(BigDecimal(3), BigDecimal(3))
        val middleNewPlotPoint = PlotPoint(BigDecimal(2), BigDecimal(2))
        val smallPlotPoint = PlotPoint(BigDecimal(1), BigDecimal(1))

        subject!!.addPointToSizedList(middleNewPlotPoint)
        subject!!.addPointToSizedList(smallPlotPoint)
        subject!!.addPointToSizedList(largePlotPoint)

        val iterator = subject!!.getPointsLargeToSmall()
        assertEquals(largePlotPoint, iterator.next())
        assertEquals(middleNewPlotPoint, iterator.next())
        assertEquals(smallPlotPoint, iterator.next())
    }

}