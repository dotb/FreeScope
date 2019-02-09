package com.squarepolka.digiscope.plot;

import com.squarepolka.digiscope.plot.plotpoint.PlotPoint;
import com.squarepolka.digiscope.plot.plotpoint.PlotPointRaw;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { PlotPointRecording.class })
public class PlotPointRecordingTest {

    @InjectMocks
    PlotPointRecording subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addSinglePlotPoint() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        assertEquals(0, subject.getCount(), 0);
        subject.addPlotPoint(firstPlotPoint);
        assertEquals(true, subject.getPoints().hasNext());
        assertEquals(1, subject.getCount(), 0);
        PlotPoint nextPlotPoint = subject.getPoints().next();
        assertEquals(firstPlotPoint, nextPlotPoint);
    }

    @Test
    public void addTwoPlotPoints() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        PlotPointRaw secondPlotPoint = new PlotPointRaw(new BigDecimal(3), new BigDecimal(4));

        subject.addPlotPoint(firstPlotPoint);
        subject.addPlotPoint(secondPlotPoint);
        assertEquals(true, subject.getPoints().hasNext());
        assertEquals(2, subject.getCount(), 0);
        PlotPointIterator iterator = subject.getPoints();
        assertEquals(firstPlotPoint, iterator.next());
        assertEquals(secondPlotPoint, iterator.next());
    }

    @Test
    public void addThreePlotPoints() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        PlotPointRaw secondPlotPoint = new PlotPointRaw(new BigDecimal(3), new BigDecimal(4));
        PlotPointRaw thirdPlotPoint = new PlotPointRaw(new BigDecimal(5), new BigDecimal(6));

        subject.addPlotPoint(firstPlotPoint);
        subject.addPlotPoint(secondPlotPoint);
        subject.addPlotPoint(thirdPlotPoint);
        assertEquals(true, subject.getPoints().hasNext());
        assertEquals(3, subject.getCount(), 0);
        PlotPointIterator iterator = subject.getPoints();
        assertEquals(firstPlotPoint, iterator.next());
        assertEquals(secondPlotPoint, iterator.next());
        assertEquals(thirdPlotPoint, iterator.next());
    }

    @Test
    public void replaceSinglePlotPoint() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        PlotPointRaw replacementPlotPoint = new PlotPointRaw(new BigDecimal(9), new BigDecimal(9));

        subject.addPlotPoint(firstPlotPoint);
        assertEquals("The recording count starts correctly", 1, subject.getCount(), 0);
        subject.replacePlotPoint(firstPlotPoint, replacementPlotPoint);
        assertEquals("The recording remains the same", 1, subject.getCount(), 0);
        assertEquals(replacementPlotPoint, subject.getPoints().next());
    }

    @Test
    public void replaceFirstOfTwoPlotPoints() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        PlotPointRaw secondPlotPoint = new PlotPointRaw(new BigDecimal(3), new BigDecimal(4));
        PlotPointRaw replacementPlotPoint = new PlotPointRaw(new BigDecimal(9), new BigDecimal(9));

        subject.addPlotPoint(firstPlotPoint);
        subject.addPlotPoint(secondPlotPoint);
        assertEquals("The recording count starts correctly", 2, subject.getCount(), 0);
        subject.replacePlotPoint(firstPlotPoint, replacementPlotPoint);
        assertEquals("The recording remains the same", 2, subject.getCount(), 0);

        PlotPointIterator iterator = subject.getPoints();
        assertEquals(replacementPlotPoint, iterator.next());
        assertEquals(secondPlotPoint, iterator.next());
    }

    @Test
    public void replaceSecondOfTwoPlotPoints() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        PlotPointRaw secondPlotPoint = new PlotPointRaw(new BigDecimal(3), new BigDecimal(4));
        PlotPointRaw replacementPlotPoint = new PlotPointRaw(new BigDecimal(9), new BigDecimal(9));

        subject.addPlotPoint(firstPlotPoint);
        subject.addPlotPoint(secondPlotPoint);
        assertEquals("The recording count starts correctly", 2, subject.getCount(), 0);
        subject.replacePlotPoint(secondPlotPoint, replacementPlotPoint);
        assertEquals("The recording remains the same", 2, subject.getCount(), 0);

        PlotPointIterator iterator = subject.getPoints();
        assertEquals(firstPlotPoint, iterator.next());
        assertEquals(replacementPlotPoint, iterator.next());
    }

    @Test
    public void replaceFirstOfThreePlotPoints() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        PlotPointRaw secondPlotPoint = new PlotPointRaw(new BigDecimal(3), new BigDecimal(4));
        PlotPointRaw thirdPlotPoint = new PlotPointRaw(new BigDecimal(5), new BigDecimal(6));
        PlotPointRaw replacementPlotPoint = new PlotPointRaw(new BigDecimal(9), new BigDecimal(9));

        subject.addPlotPoint(firstPlotPoint);
        subject.addPlotPoint(secondPlotPoint);
        subject.addPlotPoint(thirdPlotPoint);
        assertEquals("The recording count starts correctly", 3, subject.getCount(), 0);
        subject.replacePlotPoint(firstPlotPoint, replacementPlotPoint);
        assertEquals("The recording remains the same", 3, subject.getCount(), 0);

        PlotPointIterator iterator = subject.getPoints();
        assertEquals(replacementPlotPoint, iterator.next());
        assertEquals(secondPlotPoint, iterator.next());
        assertEquals(thirdPlotPoint, iterator.next());
    }

    @Test
    public void replaceSecondOfThreePlotPoints() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        PlotPointRaw secondPlotPoint = new PlotPointRaw(new BigDecimal(3), new BigDecimal(4));
        PlotPointRaw thirdPlotPoint = new PlotPointRaw(new BigDecimal(5), new BigDecimal(6));
        PlotPointRaw replacementPlotPoint = new PlotPointRaw(new BigDecimal(9), new BigDecimal(9));

        subject.addPlotPoint(firstPlotPoint);
        subject.addPlotPoint(secondPlotPoint);
        subject.addPlotPoint(thirdPlotPoint);
        assertEquals("The recording count starts correctly", 3, subject.getCount(), 0);
        subject.replacePlotPoint(secondPlotPoint, replacementPlotPoint);
        assertEquals("The recording remains the same", 3, subject.getCount(), 0);

        PlotPointIterator iterator = subject.getPoints();
        assertEquals(firstPlotPoint, iterator.next());
        assertEquals(replacementPlotPoint, iterator.next());
        assertEquals(thirdPlotPoint, iterator.next());
    }

    @Test
    public void replaceThirdOfThreePlotPoints() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        PlotPointRaw secondPlotPoint = new PlotPointRaw(new BigDecimal(3), new BigDecimal(4));
        PlotPointRaw thirdPlotPoint = new PlotPointRaw(new BigDecimal(5), new BigDecimal(6));
        PlotPointRaw replacementPlotPoint = new PlotPointRaw(new BigDecimal(9), new BigDecimal(9));

        subject.addPlotPoint(firstPlotPoint);
        subject.addPlotPoint(secondPlotPoint);
        subject.addPlotPoint(thirdPlotPoint);
        assertEquals("The recording count starts correctly", 3, subject.getCount(), 0);
        subject.replacePlotPoint(thirdPlotPoint, replacementPlotPoint);
        assertEquals("The recording remains the same", 3, subject.getCount(), 0);

        PlotPointIterator iterator = subject.getPoints();
        assertEquals(firstPlotPoint, iterator.next());
        assertEquals(secondPlotPoint, iterator.next());
        assertEquals(replacementPlotPoint, iterator.next());
    }

    @Test
    public void testVerticalGraphOutput() {
        PlotPointRaw firstPlotPoint = new PlotPointRaw(new BigDecimal(1), new BigDecimal(2));
        PlotPointRaw secondPlotPoint = new PlotPointRaw(new BigDecimal(3), new BigDecimal(4));
        PlotPointRaw thirdPlotPoint = new PlotPointRaw(new BigDecimal(5), new BigDecimal(6));

        subject.addPlotPoint(firstPlotPoint);
        subject.addPlotPoint(secondPlotPoint);
        subject.addPlotPoint(thirdPlotPoint);

        String verticalGraphExpected = "0\t1000ms\t:\t2v\n" +
                "1\t3000ms\t:\t4v\n" +
                "2\t5000ms\t:\t6v\n";
        String verticalGraphGenerated = subject.getVerticalGraph();
        assertEquals(verticalGraphExpected, verticalGraphGenerated);
    }

}