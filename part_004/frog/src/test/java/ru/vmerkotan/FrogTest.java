package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for Frog class.
 *
 * Created by vmerkotan on 1/11/2017.
 */
public class FrogTest {

    @Test
    public void whenStartAndDestinationTheSameThenReturnEmptyArray() {
        Frog frog = new Frog(new Point(1, 1), new Point(1, 1), new Field(null));
        assertThat(frog.getRoute().length, is(0));
    }

    /**
     * If sectors of start and destination points
     * are the same but circles are different
     * throw exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveImpossibleThenThrow() {
        Frog frog = new Frog(new Point(1, 1), new Point(1, 3), new Field(null));
        frog.getRoute();
    }

    /**
     * If diff between startPoint and destinationPoint is 2 sectors
     * and 2 circles then throw.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenTwoSectorsAndTowCirclesThenThrow() {
        Frog frog = new Frog(new Point(1, 1), new Point(3, 3), new Field(null));
        frog.getRoute();
    }

    /**
     * If circles to pass is more then hops
     * then throw.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenCirclesToPassIsMoreThenHopsThenThrow() {
        Frog frog = new Frog(new Point(1, 1), new Point(3, 10), new Field(null));
        frog.getRoute();
    }


    /**
     * When only one hop is required return Point[]
     * with length == 1.
     */
    @Test
    public void whenOneHopThenReturnArrayLengthOfOne() {
        Frog frog = new Frog(new Point(1, 1), new Point(4, 1), new Field(null));
        Point[] expected = new Point[]{new Point(4, 1)};

        assertThat(frog.getRoute(), is(expected));
    }

}