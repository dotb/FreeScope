package com.squarepolka.digiscope;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DigiScope {


    private static final Logger LOGGER = Logger.getLogger(DigiScope.class.getName());

    public static void main(String[] args) {
        PlotParser plotParser = PlotParser.newPlotParser("WaveData_test_arc.csv");
        PlotPointRecording plotPointRecording = plotParser.parse();

        for (PlotPointRaw plotPointRaw : plotPointRecording.getRawPoints()) {
            System.out.println(plotPointRaw.getTimeValue() + " - " + plotPointRaw.getVoltValue());
        }
    }

}


