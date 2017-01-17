package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for StackContainer class.
 *
 * Created by vmerkotan on 1/17/2017.
 */
public class StackContainerTest {

    /**
     * Test push pop methods.
     */
    @Test
    public void whenPushThenPopShouldReturnFirstAddedElement() {
        StackContainer<String> container = new StackContainer<>();
        container.push("Test1");
        container.push("Test2");
        assertThat(container.pop(), is("Test2"));
        assertThat(container.pop(), is("Test1"));
    }

}