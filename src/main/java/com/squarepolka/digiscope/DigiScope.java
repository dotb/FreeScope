package com.squarepolka.digiscope;

import com.squarepolka.digiscope.plot.PlotParser;
import com.squarepolka.digiscope.plot.PlotPointRaw;
import com.squarepolka.digiscope.plot.PlotPointRecording;

import java.util.logging.Logger;

public class DigiScope {


    private static final Logger LOGGER = Logger.getLogger(DigiScope.class.getName());

    public static void main(String[] args) {
//        PlotParser plotParser = PlotParser.newPlotParser("WaveData_test_arc.csv");
//        PlotParser plotParser = PlotParser.newPlotParser("WaveData416_flipflop_1v_40ms.csv");
//        PlotParser plotParser = PlotParser.newPlotParser("WaveData417_flipflop_1v_4ms.csv");
        PlotParser plotParser = PlotParser.newPlotParser("WaveData418_flipflop_1v_200us.csv");


        int i = 0;
        PlotPointRecording plotPointRecording = plotParser.parse();
        for (PlotPointRaw plotPointRaw : plotPointRecording.getRawPoints()) {
            System.out.println(i + "\t\t" + plotPointRaw.getTimestampMilliseconds() + "\t\t" + plotPointRaw.getVoltValue());
            i++;
        }
    }

}


