package ru.vmerkotan;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by vmerkotan on 12/15/2016.
 * tests for SortBigFile class
 */
public class SortBigFileTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * verify sort method
     */
    @Test
    public void whenPassUnOrderedThenReturnOrdered() throws IOException {

        File in = tempFolder.newFile("in.txt");
        File out = tempFolder.newFile("out.txt");

        FileWriter writer = new FileWriter(in);
        writer.write("biggest" + System.getProperty("line.separator"));
        writer.write("big" + System.getProperty("line.separator"));
        writer.write("small" + System.getProperty("line.separator"));
        writer.flush();
        writer.close();

        SortBigFile sbf = new SortBigFile();
        sbf.sort(in, out);

        RandomAccessFile raf = new RandomAccessFile(out, "r");
        List<String> actual = new ArrayList<>();
        String str;
        while((str = raf.readLine()) != null) {
            actual.add(str);
        }
        raf.close();

        List<String> expected = new ArrayList<>();
        expected.add("big");
        expected.add("small");
        expected.add("biggest");

        assertThat(actual, is(expected));
    }

}