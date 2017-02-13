package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Tests for LinkedContainer class.
 *
 * Created by vmerkotan on 1/16/2017.
 */
public class LinkedContainerTest {

    /**
     * Test add get methods.
     */
    @Test
    public void whenAddElementThenGetShouldReturnTheSame() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("String 1");
        container.add("String 2");
        container.add("String 3");
        assertThat(container.get(0), is("String 1"));
        assertThat(container.get(1), is("String 2"));
        assertThat(container.get(2), is("String 3"));
    }

    /**
     * Test size get methods.
     */
    @Test
    public void whenAddElementThenSizeShouldReturnCorrect() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("String 1");
        container.add("String 2");
        container.add("String 3");
        assertThat(container.size(), is(3));
    }

    /**
     * Test size get methods.
     */
    @Test
    public void whenAddAndRemoveElementThenSizeShouldReturnCorrect() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("String 1");
        container.add("String 2");
        container.add("String 3");
        container.remove(0);
        assertThat(container.size(), is(2));
    }


    /**
     * Test remove method.
     */
    @Test
    public void whenRemoveFirstElementThenGetShouldReturnCorrect() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("String 1");
        container.add("String 2");
        container.add("String 3");
        container.remove(0);
        assertThat(container.get(0), is("String 2"));
        assertThat(container.get(1), is("String 3"));
    }

    /**
     * Test remove method.
     */
    @Test
    public void whenRemoveLastElementThenGetShouldReturnCorrect() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("String 1");
        container.add("String 2");
        container.add("String 3");
        container.remove(2);
        assertThat(container.get(0), is("String 1"));
        assertThat(container.get(1), is("String 2"));
        assertNull(container.get(2));
    }

    /**
     * Test revert method.
     */
    @Test
    public void whenRevertThenGetShouldReturnInOpositeOrder() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("String 1");
        container.add("String 2");
        container.add("String 3");
        container.add("String 4");
        container.add("String 5");
        container.add("String 6");
        container.add("String 7");
        container.revert();
        assertThat(container.get(0), is("String 7"));
        assertThat(container.get(1), is("String 6"));
        assertThat(container.get(2), is("String 5"));
        assertThat(container.get(3), is("String 4"));
        assertThat(container.get(4), is("String 3"));
        assertThat(container.get(5), is("String 2"));
        assertThat(container.get(6), is("String 1"));


    }

}