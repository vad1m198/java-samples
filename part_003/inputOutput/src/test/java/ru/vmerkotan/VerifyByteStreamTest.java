package ru.vmerkotan;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by vmerkotan on 12/12/2016.
 * Tests for VerifyByteStream class.
 */
public class VerifyByteStreamTest {
    /**
     * VerifyByteStream instance.
     */
    private VerifyByteStream vbs;

    /**
     * initiates VerifyByteStream variable.
     */
    @Before
    public void before() {
        vbs = new VerifyByteStream();
    }
    /**
     * tests isNumber method.
     * @throws IOException when error present
     */
    @Test
    public void whenPassEvenNumberThenReturnTrue() throws IOException {
        ByteArrayInputStream bInput = new ByteArrayInputStream("2".getBytes());
        assertTrue(vbs.isNumber(bInput));
    }
    /**
     * tests isNumber method.
     * @throws IOException when error present
     */
    @Test
    public void whenPassOddNumberThenReturnFalse() throws IOException {
        ByteArrayInputStream bInput = new ByteArrayInputStream("1".getBytes());
        assertFalse(vbs.isNumber(bInput));
    }
    /**
     * tests isNumber method.
     * @throws IOException when error present
     */
    @Test
    public void whenPassNotNumberThenReturnFalse() throws IOException {
        ByteArrayInputStream bInput = new ByteArrayInputStream("string".getBytes());
        assertFalse(vbs.isNumber(bInput));
    }
}
