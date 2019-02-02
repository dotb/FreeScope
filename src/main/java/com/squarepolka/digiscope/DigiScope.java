package com.squarepolka.digiscope;

public class DigiScope {

    public static void main(String[] args) {
        PlotParser plotParser = PlotParser.newPlotParser("WaveData_test_arc.csv");
        PlotPointRecording plotPointRecording = plotParser.parse();

        for (PlotPointRaw plotPointRaw : plotPointRecording.getRawPoints()) {
            System.out.println(plotPointRaw.getTimeValue() + " - " + plotPointRaw.getVoltValue());
        }
    }

}


