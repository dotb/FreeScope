package com.squarepolka.digiscope;

import com.squarepolka.digiscope.plot.PlotParser;
import com.squarepolka.digiscope.plot.PlotPointRecording;

import java.util.logging.Logger;

public class DigiScope {


    private static final Logger LOGGER = Logger.getLogger(DigiScope.class.getName());

    public static void main(String[] args) {
//        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData_test_arc.csv");
//        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData422_flipflop_100us_1v_800us.csv");
        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData510_aircon_off.csv");

        PlotPointRecording plotPointRecording = plotParser.parse();
        System.out.println(plotPointRecording.getVerticalGraph());
        System.out.println(plotPointRecording.getBinaryDigits());

    }

}