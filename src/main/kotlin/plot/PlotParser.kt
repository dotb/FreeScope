package plot

import com.squarepolka.digiscope.exceptions.ParseException
import com.squarepolka.digiscope.plot.processors.BinaryProcessor
import com.squarepolka.digiscope.plot.processors.PulseProcessor
import plotpoint.PlotPoint
import recording.PlotPointRecording
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.math.BigDecimal
import java.util.logging.Level
import java.util.logging.Logger

class PlotParser (private val bufferedReader: BufferedReader,
                  private val pulseProcessor: PulseProcessor,
                  private val binaryProcessor: BinaryProcessor) {

    private val LOGGER = Logger.getLogger(PlotParser::class.java.name)

    companion object {
        fun newPlotParser(csvFilePath: String): PlotParser = PlotParser(BufferedReader(FileReader(csvFilePath)), PulseProcessor(), BinaryProcessor())
    }

    fun parse(): PlotPointRecording {
        val plotPointRecording = PlotPointRecording()
        try {
            val lineInputIterator = bufferedReader.lineSequence().iterator()
            while (lineInputIterator.hasNext()) {
                var line = lineInputIterator.next()
                handleParsedLine(line, plotPointRecording)
            }
        } catch (e: FileNotFoundException) {
            throw ParseException("Could not find the CSV file specified")
        } catch (e: IOException) {
            throw ParseException("IOException while trying to parse CSV file " + e.localizedMessage)
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (e: IOException) {
                    LOGGER.log(Level.SEVERE, "IOException while trying to close the CSV file buffered reader " + e.localizedMessage)
                }

            }
        }
        return plotPointRecording
    }

    private fun handleParsedLine(line: String, plotPointRecording: PlotPointRecording) {
        val data = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (data.size >= 1 && data[0].startsWith("#timebase")) { // parse the timecode #timebase=20000000000(ps)

        } else if (data.size >= 2 && data[1].startsWith("#voltbase")) { // parse the voltbase ,#voltbase=1000000(mv/100)

        } else if (data.size >= 1 && data[0].startsWith("#size")) { // parse the sample size #size=4064

        } else if (data.size >= 2) { // parse a value 2.00E-04,4.680
            parseASample(data, plotPointRecording)
        } else if (data.size >= 1 && data[0].startsWith("#")) { // log comment lines
            LOGGER.log(Level.INFO, data[0])
        } else {
            throw ParseException("A line of data could not be parsed: $line")
        }
    }

    private fun parseASample(data: Array<String>, plotPointRecording: PlotPointRecording) {
        if (data.size >= 2) { // Sample must have at both a time and volt value
            val timeValue = parseTimeValue(data[0])
            val voltValue = parseVoltValue(data[1])
            val plotPointRaw = PlotPoint(timeValue, voltValue)
            plotPointRecording.addPlotPoint(plotPointRaw)
            val processedPlotPoint = pulseProcessor.processPlotPoint(plotPointRaw, plotPointRecording)
            binaryProcessor.processPlotPoint(processedPlotPoint, plotPointRecording)
        } else {
            throw ParseException("There wasn't enough data to parse a plot point")
        }
    }

    fun parseTimeValue(timeCodeString: String): BigDecimal {
        return if (timeCodeString.length > 0) {
            BigDecimal(timeCodeString)
        } else {
            throw ParseException("Tried to parse a zero length timecode")
        }
    }

    private fun parseVoltValue(value: String): BigDecimal {
        try {
            return BigDecimal(value)
        } catch (e: NumberFormatException) {
            throw ParseException("NumberFormatException thrown when trying to parse the volt value $value")
        }

    }

}