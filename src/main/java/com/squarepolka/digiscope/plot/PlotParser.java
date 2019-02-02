package com.squarepolka.digiscope.plot;

import com.squarepolka.digiscope.exceptions.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlotParser {

    /*
        #timebase=20000 000 000(ps)  - 20ms
        ,#voltbase=1 000 000(mv/100) - 1v
        #size=4064
     */
    private static final Logger LOGGER = Logger.getLogger(PlotParser.class.getName());
    private BufferedReader bufferedReader;

    public static PlotParser newPlotParser(String csvFilePath) {
        try {
            BufferedReader newBufferedReader = new BufferedReader(new FileReader(csvFilePath));
            return new PlotParser(newBufferedReader);
        } catch (FileNotFoundException e) {
            throw new ParseException("Could not find the file " + csvFilePath);
        }
    }

    public PlotParser(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public PlotPointRecording parse() {
        PlotPointRecording plotPointRecording = new PlotPointRecording();
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                handleParsedLine(line, plotPointRecording);
            }
        } catch (FileNotFoundException e) {
            throw new ParseException("Could not find the CSV file specified");
        } catch (IOException e) {
            throw new ParseException("IOException while trying to parse CSV file " + e.getLocalizedMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "IOException while trying to close the CSV file buffered reader " + e.getLocalizedMessage());
                }
            }
        }
        return plotPointRecording;
    }

    private void handleParsedLine(String line, PlotPointRecording plotPointRecording) {
        String[] data = line.split(",");

        if (data.length >= 1 && data[0].startsWith("#timebase")) { // parse the timecode #timebase=20000000000(ps)

        } else if (data.length >= 2 && data[1].startsWith("#voltbase")) { // parse the voltbase ,#voltbase=1000000(mv/100)

        } else if (data.length >= 1 && data[0].startsWith("#size")) { // parse the sample size #size=4064

        } else if (data.length >= 2) { // parse a value 2.00E-04,4.680
            parseASample(data, plotPointRecording);
        } else {
            throw new ParseException("A line of data could not be parsed: " + line);
        }
    }

    private void parseASample(String[] data, PlotPointRecording plotPointRecording) {
        if (data.length >= 2) {
            double timeValue = parseTimeValue(data[0]);
            double voltValue = parseVoltValue(data[1]);
            PlotPointRaw plotPointRaw = new PlotPointRaw(timeValue, voltValue);
            PlotPointDigital plotPointDigital = decodePlotPointDigital(plotPointRaw);
            plotPointRecording.addPlotPoint(plotPointRaw, plotPointDigital);
            plotPointRecording.addPlotPoint(plotPointRaw, plotPointDigital);
        } else {
            throw new ParseException("There wasn't enough data to parse a plot point");
        }
    }

    private PlotPointDigital decodePlotPointDigital(PlotPointRaw plotPointRaw) {
        boolean digitalValue = false;
        if (plotPointRaw.getVoltValue() > 3) {
            digitalValue = true;
        }
        return new PlotPointDigital(plotPointRaw.getTimeValue(), digitalValue);
    }

    private double parseTimeValue(String timeCodeString) {
        if (timeCodeString.length() > 0) {
            String[] expComponents = timeCodeString.split("E-");
            double significantDigits = Double.parseDouble(expComponents[0]);
            double factor = Integer.parseInt(expComponents[1]);
            double result = significantDigits;
            for (int i = 0; i < factor; i++) {
                result /= 10;
            }
            return result;
        } else {
            throw new ParseException("Tried to parse a zero length timecode");
        }
    }

    private double parseVoltValue(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new ParseException("NumberFormatException thrown when trying to parse the volt value " + value);
        }
    }

}
