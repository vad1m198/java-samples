package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;

/**
 * Tests for SimpleLinkedSet class.
 *
 * Created by vmerkotan on 1/17/2017.
 */
public class SimpleLinkedSetTest {

    /**
     * Verify hasNext method.
     */
    @Test
    public void whenContainsThenHasNextAlwaysTrue() {
        SimpleLinkedSet<String> set = new  SimpleLinkedSet<>();
        set.add("Test");
        assertTrue(set.hasNext());
        assertTrue(set.hasNext());
    }

    /**
     * Verify hasNext method.
     */
    @Test
    public void whenContainsThenNextShouldReturnFirstElement() {
        SimpleLinkedSet<String> set = new  SimpleLinkedSet<>();
        set.add("Test");
        set.add("Test1");
        assertThat(set.next(), is("Test"));
        assertThat(set.next(), is("Test1"));
        assertFalse(set.hasNext());
    }

    /**
     * Verify duplicates.
     */
    @Test
    public void whenAddDuplicatesThenNOnlyOneElementShouldBeAdded() {
        SimpleLinkedSet<String> set = new  SimpleLinkedSet<>();
        set.add("Test");
        set.add("Test");
        assertThat(set.next(), is("Test"));
        assertFalse(set.hasNext());
    }

}