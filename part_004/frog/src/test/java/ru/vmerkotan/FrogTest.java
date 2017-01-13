package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Frog class.
 *
 * Created by vmerkotan on 1/11/2017.
 */
public class FrogTest {
    /**
     * If start and destination points are the same.
     * Then return empty path.
     */
    @Test
    public void whenStartAndDestinationTheSameThenReturnEmptyArray() {
        Frog frog = new Frog(new Point(1, 1), new Point(1, 1), new Field(null));
        assertThat(frog.getRoute(), is(""));
        assertThat(frog.getMinimalStepsNumber(), is(0));
    }

    /**
     * When no trees present then minimal oath
     * should be returned.
     */
    @Test
    public void whenFieldWithoutTreesThenReturnMinPath() {
        Frog frog = new Frog(new Point(11, 7), new Point(9, 10), new Field(null));
        assertThat(frog.getRoute(), is("[14:7] [1:7] [4:7] [6:8] [7:10] [8:8] [9:10] "));
        assertThat(frog.getMinimalStepsNumber(), is(7));
    }

    /**
     * When trees are present then path
     * should not include trees points.
     */
    @Test
    public void whenFieldWithTreesThenReturnPointsWithoutTree() {
        Frog frog = new Frog(new Point(11, 7), new Point(9, 10), new Field(new Point[]{new Point(14, 7)}));
        assertThat(frog.getRoute(), is("[13:8] [16:8] [3:8] [6:8] [7:10] [8:8] [9:10] "));
        assertThat(frog.getMinimalStepsNumber(), is(7));
    }

    /**
     * When one path one point length
     * then return only one point path.
     */
    @Test
    public void whenOnePointLengthThenReturnOneLengthPath() {
        Frog frog = new Frog(new Point(11, 7), new Point(13, 8), new Field(null));
        assertThat(frog.getRoute(), is("[13:8] "));
        assertThat(frog.getMinimalStepsNumber(), is(1));
    }

    /**
     * If move is impossible the throw.
     * If all possible first moves are occupied then throw.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveIsImpossibleThenThrow() {
        Frog frog = new Frog(new Point(11, 7), new Point(9, 10), new Field(new Point[]{new Point(14, 7),
                new Point(12, 9), new Point(12, 5), new Point(13, 8), new Point(13, 6)}));
        frog.getRoute();
    }



}