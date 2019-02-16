package com.squarepolka.digiscope.plot.factory;

import com.squarepolka.digiscope.plot.processors.PulseProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import plotpoint.PlotPoint;

import java.math.BigDecimal;

import static org.junit.Assert.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest( { PulseProcessor.class })
public class PulseProcessorTest {

    @InjectMocks
    PulseProcessor subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testBaseLinePulseDetection() {
        PlotPoint baseLinePlotPoint = new PlotPoint(new BigDecimal(0), new BigDecimal(0.04));
        PlotPoint plotPoint = new PlotPoint(new BigDecimal(0), new BigDecimal(0.5));
        boolean result = subject.isPointAtBaseline(plotPoint, baseLinePlotPoint);
        assertEquals(true, result);
    }

    @Test
    public void testPositivePulseDetection() {
        PlotPoint baseLinePlotPoint = new PlotPoint(new BigDecimal(0), new BigDecimal(0.04));
        PlotPoint plotPoint = new PlotPoint(new BigDecimal(0), new BigDecimal(4.8));
        boolean result = subject.isPointAtBaseline(plotPoint, baseLinePlotPoint);
        assertEquals(false, result);
    }

    @Test
    public void testNegativePulseDetection() {
        PlotPoint baseLinePlotPoint = new PlotPoint(new BigDecimal(0), new BigDecimal(4.76));
        PlotPoint plotPoint = new PlotPoint(new BigDecimal(0), new BigDecimal(0.04));
        boolean result = subject.isPointAtBaseline(plotPoint, baseLinePlotPoint);
        assertEquals(false, result);
    }

}
