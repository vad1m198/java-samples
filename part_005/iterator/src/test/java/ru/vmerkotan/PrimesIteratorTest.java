package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Tests for PrimesIterator class.
 * Created by Вадим on 01.01.2017.
 */
public class PrimesIteratorTest {
    /**
     * When iterator length is one then hasNext()
     * should return true.
     */
    @Test
    public void whenLengthIsOneThenHasNextFalse() {
        PrimesIterator it = new PrimesIterator(new Integer[]{1});
        assertTrue(it.hasNext());
    }

    /**
     * When iterator length is one then twice next()
     * should return null.
     */
    @Test
    public void whenLengthIsOneThenNextThrow() {
        PrimesIterator it = new PrimesIterator(new Integer[]{1});
        it.next();
        assertNull(it.next());
    }

    /**
     * When iterator length is greater 1 then next()
     * should return correct values.
     */
    @Test
    public void whenLengthIsGreaterOneThenReturnValid() {
        PrimesIterator it = new PrimesIterator(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
        Integer[] expected = new Integer[]{1, 2, 3, 5, 7, 11, 13};
        Integer[] actual = new Integer[7];
        actual[0] = it.next();
        assertTrue(it.hasNext());
        actual[1] = it.next();
        assertTrue(it.hasNext());
        actual[2] = it.next();
        assertTrue(it.hasNext());
        actual[3] = it.next();
        assertTrue(it.hasNext());
        actual[4] = it.next();
        assertTrue(it.hasNext());
        actual[5] = it.next();
        assertTrue(it.hasNext());
        actual[6] = it.next();
        assertFalse(it.hasNext());
        assertThat(actual, is(expected));
    }
}