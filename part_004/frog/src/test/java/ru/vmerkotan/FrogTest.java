package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by vmerkotan on 1/11/2017.
 */
public class FrogTest {

    @Test
    public void whenStartAndDestinationTheSameThenReturnEmptyArray() {
        Frog frog = new Frog(new Point(1, 1), new Point(1, 1), new Field(null));
        assertThat(frog.getRoute().length, is(0));
    }

    @Test(expected = RuntimeException.class)
    public void whenMoveImpossibleThenThrow() {
        Frog frog = new Frog(new Point(1, 1), new Point(1, 3), new Field(null));
        frog.getRoute();
    }

}