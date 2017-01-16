package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
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

}