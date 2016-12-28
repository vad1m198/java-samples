package ru.vmerkotan;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for FindRunner class.
 * Created by vmerkotan on 12/28/2016.
 */
public class FindRunnerTest {
    /**
     * Holds FindRunner instance.
     */
    private FindRunner fr = new FindRunner();
    /**
     * Holds test folder.
     */
    private static File testFolder;
    /**
     * Holds output folder to save results to.
     */
    private static File outputFolder;
    /**
     * Holds inner test folder.
     */
    private static File innerFolder;

    /**
     * Rule to hold temp folder.
     */
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * Setups general folders structure.
     * @throws IOException when exception appear
     */
    @Before
    public void setUp() throws IOException {
        File rootFolder = tempFolder.newFolder("RootTempFolder");
        rootFolder.mkdir();
        testFolder = new File(rootFolder.getPath() + File.separator + "testFolder");
        testFolder.mkdir();
        innerFolder = new File(testFolder.getPath() + File.separator + "innerFolder");
        innerFolder.mkdir();
        outputFolder = new File(rootFolder.getPath() + File.separator + "outputFolder");
        outputFolder.mkdir();
        File firstFile = new File(testFolder.getPath() + File.separator + "first.txt");
        firstFile.createNewFile();
        File secondFile = new File(innerFolder.getPath() + File.separator + "second.txt");
        secondFile.createNewFile();
        File firstInnerFile = new File(innerFolder.getPath() + File.separator + "first.txt");
        firstInnerFile.createNewFile();
    }

    /**
     * Clears tempFolder.
     */
    @After
    public void tearDown() {
        tempFolder.delete();
    }

    /**
     * test FindRunner class.
     * when pass valid args result should be written to -o file.
     * @throws IOException when error present
     */
    @Test
    public void whenPassValidArgsThenLogResults() throws IOException {
        String mask = "first.txt";
        String output = "log.txt";
        String[] args = new String[]{"-d", testFolder.getAbsolutePath(), "-n", mask, "-o", output};
        fr.init(args, outputFolder.getAbsolutePath());
        File outputFile = new File(outputFolder.getAbsolutePath() + File.separator + output);
        assertTrue(outputFile.exists());
        String fistLine;
        String secondLine;
        try (RandomAccessFile raf = new RandomAccessFile(outputFile, "rw")) {
            fistLine = raf.readLine();
            secondLine = raf.readLine();
        }

        assertThat(testFolder.getAbsolutePath() + File.separator + mask, is(fistLine));
        assertThat(innerFolder.getAbsolutePath() + File.separator + mask, is(secondLine));
    }

    /**
     * test FindRunner class.
     * when args length is incorrect then InvalidArgumentsException
     * @throws IOException when error present
     */
    @Test(expected = InvalidArgumentsException.class)
    public void whenArgsLengthIsIncorrectThenThrow() throws IOException {
        String[] args = new String[]{"-d", "-n", "-o"};
        fr.init(args, outputFolder.getAbsolutePath());
    }

    /**
     * test FindRunner class.
     * when start search folder is not exist then InvalidArgumentsException
     * @throws IOException when error present
     */
    @Test(expected = InvalidArgumentsException.class)
    public void whenStartFromIsNotExistThenThrow() throws IOException {
        String[] args = new String[]{"-d", "un valid path", "-n", "test.txt", "-o", "output.txt"};
        fr.init(args, outputFolder.getAbsolutePath());
    }

    /**
     * test FindRunner class.
     * when output is not relative then InvalidArgumentsException
     * @throws IOException when error present
     */
    @Test(expected = InvalidArgumentsException.class)
    public void whenOutputIsNotRelativeThenThrow() throws IOException {
        String[] args = new String[]{"-d", testFolder.getAbsolutePath(), "-n", "test.txt", "-o", testFolder.getAbsolutePath()};
        fr.init(args, outputFolder.getAbsolutePath());
    }
}