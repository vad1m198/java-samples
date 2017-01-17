package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/** tests for SimpleArraySet class.
 *
 * Created by vmerkotan on 1/17/2017.
 */
public class SimpleArraySetTest {
    /**
     * Verify hasNext method.
     */
    @Test
    public void whenContainsThenHasNextAlwaysTrue() {
        SimpleArraySet<String> set = new SimpleArraySet<>();
        set.add("Test");
        assertTrue(set.hasNext());
        assertTrue(set.hasNext());
    }

    /**
     * Verify hasNext method.
     */
    @Test
    public void whenContainsThenNextShouldReturnFirstElement() {
        SimpleArraySet<String> set = new SimpleArraySet<>();
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
    public void whenAddDuplicatesThenOnlyOneElementShouldBeAdded() {
        SimpleArraySet<String> set = new SimpleArraySet<>();
        set.add("Test");
        set.add("Test");
        assertThat(set.next(), is("Test"));
        assertFalse(set.hasNext());
    }

    /**
     * Verify sort order.
     * When add Objects.
     * Next should return values based on it hashcode.
     */
    @Test
    public void whenAddValuesThenNextShouldReturnInOrder() {
        SimpleArraySet<String> set = new SimpleArraySet<>();
        set.add("test");
        set.add("String");
        set.add("new");

        assertThat(set.next(), is("String"));
        assertThat(set.next(), is("new"));
        assertThat(set.next(), is("test"));
        assertFalse(set.hasNext());
    }


}