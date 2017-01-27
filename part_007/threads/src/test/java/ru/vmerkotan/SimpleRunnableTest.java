package ru.vmerkotan;

import org.junit.Test;

/**
 * Tests for SimpleRunnable class.
 *
 * Created by vmerkotan on 1/27/2017.
 */
public class SimpleRunnableTest {

    @Test
    public void countWhiteSpacesAndSomeChars() throws InterruptedException {
        String testTxt = "Some text to test work of the program on";
        SimpleRunnable spaces = new SimpleRunnable(testTxt, ' ');
        SimpleRunnable chars = new SimpleRunnable(testTxt, 't');

        (new Thread(spaces)).start();
        (new Thread(chars)).start();
        Thread.sleep(1000);

    }

}