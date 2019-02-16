package com.squarepolka.digiscope;

import com.squarepolka.digiscope.plot.PlotParser;
import com.squarepolka.digiscope.plot.recording.PlotPointRecording;

import java.util.logging.Logger;

public class DigiScope {


    private static final Logger LOGGER = Logger.getLogger(DigiScope.class.getName());

    public static void main(String[] args) {
//        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData_test_arc.csv");
//        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData422_flipflop_100us_1v_800us.csv");
//        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData510_aircon_off.csv");
        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData530_amp_volume_down.csv");

        PlotPointRecording plotPointRecording = plotParser.parse();
        System.out.println(plotPointRecording.getPlotPoints());
        System.out.println(plotPointRecording.getBinaryDigits());

    }

}