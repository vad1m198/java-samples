package ru.vmerkotan;

import org.junit.*;
import org.junit.rules.TemporaryFolder;
import ru.vmerkotan.output.Output;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for TextSearcher class.
 *
 * Created by Вадим on 28.01.2017.
 */
public class TextSearcherTest {

    private static File tmpFolder;

    @ClassRule
    public static TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void before() throws IOException {
        tmpFolder = folder.newFolder();
        Path firstFile = Paths.get(tmpFolder.getAbsolutePath(), "testFile1.txt");
        Path secondFile = Paths.get(tmpFolder.getAbsolutePath(), "testFile2.txt");
        Files.createFile(firstFile);
        Files.createFile(secondFile);

        try(BufferedWriter first = Files.newBufferedWriter(firstFile)) {
            try(BufferedWriter second = Files.newBufferedWriter(secondFile)) {
                for(int i = 0; i < 1000; i++) {
                    first.write("First file. String " + i + System.getProperty("line.separator"));
                    second.write("Second file. String " + i + System.getProperty("line.separator"));
                }
            }
        }
    }

    @AfterClass
    public static void after() {
        folder.delete();
    }

    @Test
    public void InterruptOnFindFalseThenSeveralResults() throws IOException {
        TestOutput out = new TestOutput();
        TextSearcher searcher = new TextSearcher(out);
        searcher.init(tmpFolder.toPath(), "String 500", false);
        assertThat(out.getResult(), containsString("First file. String 500"));
        assertThat(out.getResult(), containsString("Second file. String 500"));
    }

    @Test
    public void InterruptOnFindTrueThenOneResults() throws IOException {
        TestOutput out = new TestOutput();
        TextSearcher searcher = new TextSearcher(out);
        searcher.init(tmpFolder.toPath(), "String 100", true);
        assertThat(out.getResult().indexOf("String 100"), is(out.getResult().lastIndexOf("String 100")));
        assertThat(out.getResult().indexOf("String 100"), greaterThan(-1));
    }

    private class TestOutput implements Output {
        private StringBuilder sb = new StringBuilder();
        @Override
        public void write(String output) {
            sb.append(output);
        }

        String getResult() {
            return sb.toString();
        }
    }

}