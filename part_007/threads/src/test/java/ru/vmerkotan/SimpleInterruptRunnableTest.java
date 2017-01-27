package ru.vmerkotan;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vmerkotan on 1/27/2017.
 */
public class SimpleInterruptRunnableTest {

    @Test
    public void countWhiteSpacesAndSomeChars() throws InterruptedException {
        String testTxt = "Some text to test work of the program on";
        char spaceChar = ' ';
        char tChar = 't';
        System.out.println("Test string: " + testTxt);

        SimpleInterruptRunnable spacesRunnable = new SimpleInterruptRunnable(testTxt, spaceChar);
        SimpleInterruptRunnable charsRunnable = new SimpleInterruptRunnable(testTxt, tChar);

        Thread spaces = new Thread(spacesRunnable);
        Thread chars = new Thread(charsRunnable);

        chars.start();
        spaces.start();

        spaces.join(1000);
        chars.join(1000);

        spaces.interrupt();
        chars.interrupt();

        System.out.println("Contains " + spacesRunnable.getCounter() + " '" + spaceChar + "'");
        System.out.println("Contains " + charsRunnable.getCounter() + " '" + tChar + "'");

    }

}