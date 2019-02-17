package plot

import com.squarepolka.digiscope.plot.processors.BinaryProcessor
import com.squarepolka.digiscope.plot.processors.PulseProcessor
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

import java.io.BufferedReader
import java.io.IOException

class PlotParserTest {

    private val bufferedReader: BufferedReader = Mockito.mock(BufferedReader::class.java)
    private val pulseProcessor: PulseProcessor = Mockito.mock(PulseProcessor::class.java)
    private val binaryProcessor: BinaryProcessor = Mockito.mock(BinaryProcessor::class.java)
    private var subject: PlotParser = PlotParser(bufferedReader, pulseProcessor, binaryProcessor)

    @Test
    @Throws(IOException::class)
    fun testParsingASingleValue() {
        Mockito.`when`(bufferedReader.readLine()).thenReturn("1.01E-04,4.680", null)
        val plotPointRecording = subject.parse()
        val iterator = plotPointRecording.getPoints()
        val plotPointRaw = iterator.next()
        assertEquals(0.000101, plotPointRaw.timestampSeconds.toDouble(), 0.0)
        assertEquals(4.68, plotPointRaw.voltValue.toDouble(), 0.0)
        assertEquals("Ensure there is only one value in the recording", false, iterator.hasNext())
    }

    @Test
    @Throws(IOException::class)
    fun testParsingTwoValues() {
        Mockito.`when`(bufferedReader.readLine()).thenReturn("1.01E-04,4.680", "4.20E+03,2.570", null)
        val plotPointRecording = subject.parse()
        val iterator = plotPointRecording.getPoints()

        val plotPointRawOne = iterator.next()
        assertEquals(0.000101, plotPointRawOne.timestampSeconds.toDouble(), 0.0)
        assertEquals(4.68, plotPointRawOne.voltValue.toDouble(), 0.0)

        val plotPointRawTwo = iterator.next()
        assertEquals(4200.0, plotPointRawTwo.timestampSeconds.toDouble(), 0.0)
        assertEquals(2.57, plotPointRawTwo.voltValue.toDouble(), 0.0)

        assertEquals("Ensure there are only two values in the recording", false, iterator.hasNext())
    }


    @Test
    fun parseNegTimeCode1() {
        val result = subject.parseTimeValue("1.0E-02")
        assertEquals(0.010, result.toDouble(), 0.0)
    }

    @Test
    fun parseNegTimeCode2() {
        val result = subject.parseTimeValue("7.25E-04")
        assertEquals(0.000725, result.toDouble(), 0.0)
    }

    @Test
    fun parseNegTimeCode3() {
        val result = subject.parseTimeValue("2.63E-03")
        assertEquals(0.00263, result.toDouble(), 0.0)
    }

    @Test
    fun parseNegTimeCode4() {
        val result = subject.parseTimeValue("1.28E-03")
        assertEquals(0.001280, result.toDouble(), 0.0)
    }

    @Test
    fun parsePosTimeCode1() {
        val result = subject.parseTimeValue("1.0E+02")
        assertEquals(100.0, result.toDouble(), 0.0)
    }

    @Test
    fun parsePosTimeCode2() {
        val result = subject.parseTimeValue("7.25E+04")
        assertEquals(72500.0, result.toDouble(), 0.0)
    }
}