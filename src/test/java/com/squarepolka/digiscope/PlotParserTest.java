package com.squarepolka.digiscope;


import com.squarepolka.digiscope.plot.PlotParser;
import com.squarepolka.digiscope.plot.plotpoint.PlotPointRaw;
import com.squarepolka.digiscope.plot.PlotPointRecording;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { PlotParser.class })
public class PlotParserTest {

    @Mock
    private BufferedReader bufferedReader;

    @InjectMocks
    private PlotParser subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    public PlotParserTest() {
        // PowerMock wants this to be here, and empty.
    }

    @Test
    public void testParsingASingleValue() throws IOException {
        Mockito.when(bufferedReader.readLine()).thenReturn("1.01E-04,4.680", null);
        PlotPointRecording plotPointRecording = subject.parse();
        Iterable<PlotPointRaw> result = plotPointRecording.getRawPoints();
        Iterator<PlotPointRaw> iterator = result.iterator();
        PlotPointRaw plotPointRaw = iterator.next();
        assertEquals(0.000101, plotPointRaw.getTimestampSeconds().doubleValue(), 0);
        assertEquals(4.68, plotPointRaw.getVoltValue().doubleValue(), 0);
        assertEquals("Ensure there is only one value in the recording", false, iterator.hasNext());
    }

    @Test
    public void testParsingTwoValues() throws IOException {
        Mockito.when(bufferedReader.readLine()).thenReturn("1.01E-04,4.680", "4.20E+03,2.570", null);
        PlotPointRecording plotPointRecording = subject.parse();
        Iterable<PlotPointRaw> result = plotPointRecording.getRawPoints();
        Iterator<PlotPointRaw> iterator = result.iterator();

        PlotPointRaw plotPointRawOne = iterator.next();
        assertEquals(0.000101, plotPointRawOne.getTimestampSeconds().doubleValue(), 0);
        assertEquals(4.68, plotPointRawOne.getVoltValue().doubleValue(), 0);

        PlotPointRaw plotPointRawTwo = iterator.next();
        assertEquals(4200.0, plotPointRawTwo.getTimestampSeconds().doubleValue(), 0);
        assertEquals(2.57, plotPointRawTwo.getVoltValue().doubleValue(), 0);

        assertEquals("Ensure there are only two values in the recording", false, iterator.hasNext());
    }


    @Test
    public void parseNegTimeCode1() {
        BigDecimal result = subject.parseTimeValue("1.0E-02");
        assertEquals(0.010, result.doubleValue(), 0);
    }

    @Test
    public void parseNegTimeCode2() {
        BigDecimal result = subject.parseTimeValue("7.25E-04");
        assertEquals(0.000725, result.doubleValue(), 0);
    }

    @Test
    public void parseNegTimeCode3() {
        BigDecimal result = subject.parseTimeValue("2.63E-03");
        assertEquals(0.00263, result.doubleValue(), 0);
    }

    @Test
    public void parseNegTimeCode4() {
        BigDecimal result = subject.parseTimeValue("1.28E-03");
        assertEquals(0.001280, result.doubleValue(), 0);
    }

    @Test
    public void parsePosTimeCode1() {
        BigDecimal result = subject.parseTimeValue("1.0E+02");
        assertEquals(100.0, result.doubleValue(), 0);
    }

    @Test
    public void parsePosTimeCode2() {
        BigDecimal result = subject.parseTimeValue("7.25E+04");
        assertEquals(72500.0, result.doubleValue(), 0);
    }

}