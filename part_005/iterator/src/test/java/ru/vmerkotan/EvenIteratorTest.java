package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
        EvenIterator<Integer> it = new EvenIterator<>(new Integer[]{1});
        assertTrue(it.hasNext());
    }

    /**
     * If Iterator has one element then next()
     * return this element.
     */
    @Test
    public void whenLengthIsOneThenNextThrow() {
        EvenIterator<Integer> it = new EvenIterator<>(new Integer[]{1});
        assertThat(it.next(), is(1));
    }

    /**
     * If Iterator has two elements and next() was invoked
     * then hasNext() should return false.
     */
    @Test
    public void whenLengthIsTwoThenHasNextReturnFalse() {
        EvenIterator<Integer> it = new EvenIterator<>(new Integer[]{1, 3});
        it.next();
        assertFalse(it.hasNext());
    }

    /**
     * If Iterator has zero elements
     * then hasNext() should return false.
     */
    @Test
    public void whenLengthIsZeroThenHasNextReturnFalse() {
        EvenIterator<Integer> it = new EvenIterator<>(new Integer[]{});
        assertFalse(it.hasNext());
    }

    /**
     * If Iterator has two element and next()
     * was invoked twice then throw
     * return second element.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenLengthIsTwoThenTwiceNextThrow() {
        EvenIterator<Integer> it = new EvenIterator<>(new Integer[]{1, 3});
        it.next();
        it.next();
    }

    /**
     * If Iterator has six elements
     * then next() should return first, third, fifth.
     */
    @Test
    public void whenLengthSixThenNextReturnelements() {
        EvenIterator<Integer> it = new EvenIterator<>(new Integer[]{1, 3, 5, 7, 10, 15});
        int[] expected = new int[]{1, 5, 10};
        int[] actual = new int[3];
        actual[0] = it.next();
        actual[1] = it.next();
        actual[2] = it.next();

        assertThat(actual, is(expected));
    }
}