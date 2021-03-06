package ru.vmerkotan;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by vmerkotan on 12/15/2016.
 * tests for SortBigFile class.
 */
public class SortBigFileTest {
    /**
     * Temp folder JUnit rule.
     */
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * verify sort method.
     * @throws IOException when error present
     */
    @Test
    public void whenPassUnOrderedThenReturnOrdered() throws IOException {

        File in = tempFolder.newFile("in.txt");
        File out = tempFolder.newFile("out.txt");
        final int CAPACITY = 100;
        FileWriter writer = new FileWriter(in);
        for (int i = 0; i < CAPACITY; i++) {
            writer.write("biggest" + System.getProperty("line.separator"));
            writer.write("big" + System.getProperty("line.separator"));
            writer.write("small" + System.getProperty("line.separator"));
        }
        writer.flush();
        writer.close();

        SortBigFile sbf = new SortBigFile();
//        long starttime = System.currentTimeMillis();
        sbf.sort(in, out);
//        long endtime = System.currentTimeMillis();
//        System.out.println("Test took: " +  (endtime - starttime) / 1000 + " seconds");
        RandomAccessFile raf = new RandomAccessFile(out, "r");
        List<String> actual = new ArrayList<>();
        String str;
        while ((str = raf.readLine()) != null) {
            actual.add(str);
        }
        raf.close();

        List<String> expected = new ArrayList<>();
        for (int i = 0; i < CAPACITY; i++) {
            expected.add("big");
        }
        for (int i = 0; i < CAPACITY; i++) {
            expected.add("small");
        }
        for (int i = 0; i < CAPACITY; i++) {
            expected.add("biggest");
        }
        assertThat(actual, is(expected));
    }

}
