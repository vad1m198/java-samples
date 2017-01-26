package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Tests for Tree class.
 *
 * Created by vmerkotan on 1/26/2017.
 */
public class TreeTest {

    /**
     * Test put method.
     */
    @Test
    public void whenPutDuplicatesThenSizeShouldNotChanged() {
        Tree<Integer> tree = new Tree<>();
        tree.put(15);
        tree.put(11);
        tree.put(14);
        tree.put(22);
        tree.put(18);
        tree.put(25);
        tree.put(5);
        tree.put(5);
        assertThat(tree.size(), is(7));
    }

    /**
     * Test contains method.
     */
    @Test
    public void whenElementIsPresentThenContainsTrue() {
        Tree<Integer> tree = new Tree<>();
        tree.put(15);
        tree.put(11);
        tree.put(14);
        tree.put(22);
        assertTrue(tree.contains(22));
    }

    /**
     * Test contains method.
     */
    @Test
    public void whenElementIsAbsentThenContainsFalse() {
        Tree<Integer> tree = new Tree<>();
        tree.put(15);
        tree.put(11);
        tree.put(14);
        tree.put(null);
        assertFalse(tree.contains(28));
    }

}