package com.squarepolka.digiscope;


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

//    PlotParser subject = PowerMockito.spy(new PlotParser(bufferedReader));

    public PlotParserTest() {

    }

    /*@Test
    public void parseTimeCode1() {
        double result = subject.parseTimeValue("1.0E-02");
        assertEquals(100, result, 0);
    }

    @Test
    public void parseTimeCode2() {
        double result = subject.parseTimeValue("7.25E-04");
        assertEquals(72500, result, 0);
    }

    @Test
    public void parseTimeCode3() {
        double result = subject.parseTimeValue("2.63E-03");
        assertEquals(2630, result, 0);
    }

    @Test
    public void parseTimeCode4() {
        double result = subject.parseTimeValue("1.28E-03");
        assertEquals(1280, result, 0);
    }*/


}