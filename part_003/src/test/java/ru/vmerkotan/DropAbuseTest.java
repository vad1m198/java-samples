package ru.vmerkotan;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static org.junit.Assert.*;

/**
 * Created by vmerkotan on 12/12/2016.
 * Tests for DropAbuse class
 */
public class DropAbuseTest {

    static DropAbuse da = new DropAbuse();

    /**
     * tests dropAbuses method
     * If abuse exist then return OutputStream without abuses
     * @throws IOException
     */
    @Test
    public void whenAbusesExistThenReturnWithoutAbuses () throws IOException {
        ByteArrayOutputStream bOutputExpected = new ByteArrayOutputStream();

        String[] abuse = new String[]{"new", "123"};
        String testString = "newold123";
        da.dropAbuses(new ByteArrayInputStream(testString.getBytes()), bOutputExpected, abuse);
        assertEquals("old", bOutputExpected.toString());
    }

    /**
     * tests dropAbuses method
     * If multiple abuse exist then return OutputStream without abuses
     * @throws IOException
     */
    @Test
    public void whenMultipleAbusesExistThenReturnWithoutAllAbuses () throws IOException {
        ByteArrayOutputStream bOutputExpected = new ByteArrayOutputStream();
        String[] abuse = new String[]{"new", "123"};
        String testString = "newold123new123old";
        da.dropAbuses(new ByteArrayInputStream(testString.getBytes()), bOutputExpected, abuse);
        assertEquals("oldold", bOutputExpected.toString());
    }

    /**
     * tests dropAbuses method
     * If no abuse exist then return OutputStream without changes
     * @throws IOException
     */
    @Test
    public void whenNoAbusesThenReturnOrigin () throws IOException {
        ByteArrayOutputStream bOutputExpected = new ByteArrayOutputStream();
        String[] abuse = new String[]{"new", "123"};
        String testString = "old567";
        da.dropAbuses(new ByteArrayInputStream(testString.getBytes()), bOutputExpected, abuse);
        assertEquals("old567", bOutputExpected.toString());
    }

    /**
     * tests dropAbuses method
     * If abuse part exist then return OutputStream without changes
     * @throws IOException
     */
    @Test
    public void whenPartAbuseExistThenReturnOrigin () throws IOException {
        ByteArrayOutputStream bOutputExpected = new ByteArrayOutputStream();
        String[] abuse = new String[]{"new", "123"};
        String testString = "nneold127";
        da.dropAbuses(new ByteArrayInputStream(testString.getBytes()), bOutputExpected, abuse);
        assertEquals("nneold127", bOutputExpected.toString());
    }
}
