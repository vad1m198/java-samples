package ru.vmerkotan;

import org.junit.Test;

/**
 * Tests for SimpleWritableRunnable class.
 *
 * Created by vmerkotan on 1/27/2017.
 */
public class SimpleRunnableTest {

    @Test
    public void countWhiteSpacesAndSomeChars() throws InterruptedException {
        String testTxt = "Some text to test work of the program on";
        SimpleWritableRunnable spaces = new SimpleWritableRunnable(testTxt, ' ');
        SimpleWritableRunnable chars = new SimpleWritableRunnable(testTxt, 't');

        (new Thread(spaces)).start();
        (new Thread(chars)).start();
        Thread.sleep(1000);

    }

}