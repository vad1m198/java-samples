package ru.vmerkotan;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by vmerkotan on 12/15/2016.
 * Tests for Palindrome class.
 */
public class PalindromeTest {

    /**
     * tests isPolindrom method.
     */
    @Test
    public void whenPassPolindromThenTrue() {
        Palindrome p = new Palindrome();
        assertTrue(p.isPolindrom("rotor"));
    }

    /**
     * tests isPolindrom method.
     */
    @Test
    public void whenPassPolindromInDiffCasesThenTrue() {
        Palindrome p = new Palindrome();
        assertTrue(p.isPolindrom("rotOR"));
    }

    /**
     * tests isPolindrom method.
     */
    @Test
    public void whenPassNotPolindromThenFalse() {
        Palindrome p = new Palindrome();
        assertFalse(p.isPolindrom("rotod"));
    }

    /**
     * tests isPolindrom method.
     */
    @Test
    public void whenPassPolindromWithLengthNotFiveThenFalse() {
        Palindrome p = new Palindrome();
        assertFalse(p.isPolindrom("rotodotor"));
    }
}