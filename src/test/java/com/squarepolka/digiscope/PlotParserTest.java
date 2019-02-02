package com.squarepolka.digiscope;


import com.squarepolka.digiscope.plot.PlotParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { PlotParser.class })
class PlotParserTest {

    @Mock
    BufferedReader bufferedReader;

    PlotParser subject = new PlotParser(bufferedReader);

    public PlotParserTest() {
        // PowerMock wants this to be here, and empty.
    }

    @Test
    public void parseNegTimeCode1() {
        double result = subject.parseTimeValue("1.0E-02");
        assertEquals(0.010, result, 0);
    }

    @Test
    public void parseNegTimeCode2() {
        double result = subject.parseTimeValue("7.25E-04");
        assertEquals(0.000725, result, 0);
    }

    @Test
    public void parseNegTimeCode3() {
        double result = subject.parseTimeValue("2.63E-03");
        assertEquals(0.00263, result, 0);
    }

    @Test
    public void parseNegTimeCode4() {
        double result = subject.parseTimeValue("1.28E-03");
        assertEquals(0.001280, result, 0);
    }

    @Test
    public void parsePosTimeCode1() {
        double result = subject.parseTimeValue("1.0E+02");
        assertEquals(100.0, result, 0);
    }

    @Test
    public void parsePosTimeCode2() {
        double result = subject.parseTimeValue("7.25E+04");
        assertEquals(72500.0, result, 0);
    }

}