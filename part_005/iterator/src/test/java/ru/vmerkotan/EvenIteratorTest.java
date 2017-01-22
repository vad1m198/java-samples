package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Tests for EvenIterator class.
 *
 * Created by Вадим on 01.01.2017.
 */
public class EvenIteratorTest {

    /**
     * If Iterator has one element then
     * hasNext() should return false.
     */
    @Test
    public void whenLengthIsOneThenHasNextReturnTrue() {
        EvenIterator it = new EvenIterator(new Integer[]{2});
        assertTrue(it.hasNext());
    }

    /**
     * If Iterator has one element then next()
     * return this element.
     */
    @Test
    public void whenLengthIsOneThenNextThrow() {
        EvenIterator it = new EvenIterator(new Integer[]{4});
        assertThat(it.next(), is(4));
    }

    /**
     * If Iterator has two elements and next() was invoked
     * then hasNext() should return false.
     */
    @Test
    public void whenLengthIsTwoThenHasNextReturnFalse() {
        EvenIterator it = new EvenIterator(new Integer[]{2});
        it.next();
        assertFalse(it.hasNext());
    }

    /**
     * If Iterator has zero elements
     * then hasNext() should return false.
     */
    @Test
    public void whenLengthIsZeroThenHasNextReturnFalse() {
        EvenIterator it = new EvenIterator(new Integer[]{});
        assertFalse(it.hasNext());
    }

    /**
     * If Iterator has one element and next()
     * was invoked twice then return null.
     */
    @Test
    public void whenLengthIsTwoThenTwiceNextThrow() {
        EvenIterator it = new EvenIterator(new Integer[]{2});
        it.next();
        assertNull(it.next());
    }

    /**
     * If Iterator has six elements
     * then next() should return first, third, fifth.
     */
    @Test
    public void whenLengthSixThenNextReturnElements() {
        EvenIterator it = new EvenIterator(new Integer[]{2, 3, 4, 7, 6, 15});
        Integer[] expected = new Integer[]{2, 4, 6};
        Integer[] actual = new Integer[3];
        actual[0] = it.next();
        actual[1] = it.next();
        actual[2] = it.next();

        assertThat(actual, is(expected));
    }
}