package com.squarepolka.digiscope;

import com.squarepolka.digiscope.plot.PlotParser;
import com.squarepolka.digiscope.plot.plotpoint.PlotPointRaw;
import com.squarepolka.digiscope.plot.PlotPointRecording;

import java.util.logging.Logger;

public class DigiScope {


    private static final Logger LOGGER = Logger.getLogger(DigiScope.class.getName());

    public static void main(String[] args) {
//        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData_test_arc.csv");
//        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData422_flipflop_100us_1v_800us.csv");
        PlotParser plotParser = PlotParser.newPlotParser("test_data/WaveData424_flipflop_100us_02v_800us.csv");

        int i = 0;
        PlotPointRecording plotPointRecording = plotParser.parse();
        for (PlotPointRaw plotPoint : plotPointRecording.getPulsePoints()) {
            System.out.println(i + "\t\t" + plotPoint);
            i++;
        }
    }

}