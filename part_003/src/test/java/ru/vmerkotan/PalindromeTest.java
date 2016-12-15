package ru.vmerkotan;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vmerkotan on 12/15/2016.
 * Tests for Palindrome class
 */
public class PalindromeTest {

    @Test
    public void whenPassPolindromThenTrue() {
        Palindrome p = new Palindrome();
        assertTrue(p.isPolindrom("rotor"));
    }

    @Test
    public void whenPassPolindromInDiffCasesThenTrue() {
        Palindrome p = new Palindrome();
        assertTrue(p.isPolindrom("rotOR"));
    }

    @Test
    public void whenPassNotPolindromThenFalse() {
        Palindrome p = new Palindrome();
        assertFalse(p.isPolindrom("rotod"));
    }

    @Test
    public void whenPassPolindromWithLengthNotFiveThenFalse() {
        Palindrome p = new Palindrome();
        assertFalse(p.isPolindrom("rotodotor"));
    }
}