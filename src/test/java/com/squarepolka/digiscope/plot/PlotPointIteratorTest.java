package com.squarepolka.digiscope.plot;

import com.squarepolka.digiscope.plot.plotpoint.PlotPointRaw;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { PlotPointIterator.class })
public class PlotPointIteratorTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void worksWithOneElement() {
        PlotPointRaw firstPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(1));
        PlotPointIterator subject = new PlotPointIterator(firstPoint);
        assertEquals(true, subject.hasNext());
        assertEquals(firstPoint, subject.next());
        assertEquals(false, subject.hasNext()); // Should have no more
    }

    @Test
    public void worksWithTwoElements() {
        PlotPointRaw firstPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(1));
        PlotPointRaw secondPoint = new PlotPointRaw(new BigDecimal(2), new BigDecimal(2));
        firstPoint.setNextPlotPoint(secondPoint);
        secondPoint.setPreviousPoint(firstPoint);

        PlotPointIterator subject = new PlotPointIterator(firstPoint);

        // Pop off the first element
        assertEquals(true, subject.hasNext());
        assertEquals(firstPoint, subject.next());

        // Pop off the second element
        assertEquals(true, subject.hasNext());
        assertEquals(secondPoint, subject.next());
        assertEquals(false, subject.hasNext()); // Should have no more
    }

    @Test
    public void worksWithThreeElements() {
        PlotPointRaw firstPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(1));
        PlotPointRaw secondPoint = new PlotPointRaw(new BigDecimal(2), new BigDecimal(2));
        PlotPointRaw thirdPoint = new PlotPointRaw(new BigDecimal(3), new BigDecimal(3));
        firstPoint.setNextPlotPoint(secondPoint);
        secondPoint.setNextPlotPoint(thirdPoint);
        thirdPoint.setPreviousPoint(secondPoint);
        secondPoint.setPreviousPoint(firstPoint);

        PlotPointIterator subject = new PlotPointIterator(firstPoint);

        // Pop off the first element
        assertEquals(true, subject.hasNext());
        assertEquals(firstPoint, subject.next());

        // Pop off the second element
        assertEquals(true, subject.hasNext());
        assertEquals(secondPoint, subject.next());

        // Pop off the third element
        assertEquals(true, subject.hasNext());
        assertEquals(thirdPoint, subject.next());
        assertEquals(false, subject.hasNext()); // Should have no more
    }
}