package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Accumulations class.
 *
 * Created by vmerkotan on 1/19/2017.
 */
public class AccumulationsTest {
    /**
     * test findAccumulationsAmount method.
     */
    @Test
    public void whenPassValidArrayThenReturnValidNumber() {
        int[][] arr = new int[][]{
                {1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0}
        };

        Accumulations ac = new Accumulations(arr);
        ac.findAccumulationsAmount();

        assertThat(ac.getMax(), is(6));
    }
}