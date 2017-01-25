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
        String test1 = "Test1";
        String test = "Test";
        set.add(test);
        set.add(test1);

        assertThat(set.next(), is("Test1"));
        assertThat(set.next(), is("Test"));
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
}