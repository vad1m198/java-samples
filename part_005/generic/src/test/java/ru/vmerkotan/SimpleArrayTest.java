package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for SimpleArray class.
 *
 * Created by vmerkotan on 1/16/2017.
 */
public class SimpleArrayTest {
    /**
     * when add one record then get(0)
     * returns passed value.
     */
    @Test
    public void whenAddOneThenGetZeroReturnAddedValue() {
        SimpleArray<String> arr = new SimpleArray<>(10);
        arr.add("Test");

        assertThat(arr.get(0), is("Test"));
    }

    /**
     * when go over capacity then
     * should work correctly.
     */
    @Test
    public void whenOverCapacityThenShouldWork() {
        SimpleArray<String> arr = new SimpleArray<>(1);
        arr.add("Test");
        arr.add("Test1");
        arr.add("Test2");

        assertThat(arr.get(2), is("Test2"));
    }

    /**
     * when update then passed index should
     * be updated with correct value.
     */
    @Test
    public void whenUpdateThenValueShouldBeChanged() {
        SimpleArray<String> arr = new SimpleArray<>(3);
        arr.add("Test");
        arr.add("Test1");
        arr.update(1, "Test2");

        assertThat(arr.get(1), is("Test2"));
    }

    /**
     * when delete last value then when invoke add
     * should be added to the same index.
     */
    @Test
    public void whenDeleteAndAddThenShouldBeUpdated() {
        SimpleArray<String> arr = new SimpleArray<>(3);
        arr.add("Test");
        arr.add("Test1");
        arr.delete(1);
        arr.add("Test 2");

        assertThat(arr.get(1), is("Test 2"));
    }


    /**
     * when delete in the middle then
     * array should shift appropriately.
     */
    @Test
    public void whenDeleteInMiddleThenShouldBeShifted() {
        SimpleArray<String> arr = new SimpleArray<>(3);
        arr.add("Test");
        arr.add("Test1");
        arr.add("Test 2");
        arr.delete(1);

        assertThat(arr.get(1), is("Test 2"));
    }

}