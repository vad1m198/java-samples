package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for QueueContainer class.
 *
 * Created by vmerkotan on 1/17/2017.
 */
public class QueueContainerTest {
    /**
     * Test push pop methods.
     */
    @Test
    public void whenPushThenPopShouldReturnLastAddedElement() {
        QueueContainer<String> container = new QueueContainer<>();
        container.push("Test1");
        container.push("Test2");
        assertThat(container.pop(), is("Test1"));
        assertThat(container.pop(), is("Test2"));
    }

}