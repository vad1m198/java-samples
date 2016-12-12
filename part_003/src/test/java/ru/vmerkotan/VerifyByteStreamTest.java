package ru.vmerkotan;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by vmerkotan on 12/12/2016.
 * Tests for VerifyByteStream class
 */
public class VerifyByteStreamTest {

    VerifyByteStream vbs;
    ByteArrayOutputStream bOutput;

    @Before
    public void before() {
        vbs = new VerifyByteStream();
        bOutput = new ByteArrayOutputStream(1);
    }

    @Test
    public void whenPassEvenNumberThenReturnTrue() throws IOException {
        bOutput.write("2".getBytes());
        byte b [] = bOutput.toByteArray();
        ByteArrayInputStream bInput = new ByteArrayInputStream(b);
        assertTrue(vbs.isNumber(bInput));
    }

    @Test
    public void whenPassOddNumberThenReturnFalse() throws IOException {
        bOutput.write("1".getBytes());
        byte b [] = bOutput.toByteArray();
        ByteArrayInputStream bInput = new ByteArrayInputStream(b);
        assertFalse(vbs.isNumber(bInput));
    }

    @Test
    public void whenPassNotNumberThenReturnFalse() throws IOException {
        bOutput.write("string".getBytes());
        byte b [] = bOutput.toByteArray();
        ByteArrayInputStream bInput = new ByteArrayInputStream(b);
        assertFalse(vbs.isNumber(bInput));
    }
}
