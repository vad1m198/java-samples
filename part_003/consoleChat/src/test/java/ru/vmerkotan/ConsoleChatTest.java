package ru.vmerkotan;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by vmerkotan on 12/20/2016.
 * Tests for ConsoleChat class
 */
public class ConsoleChatTest {

    private static File in;
    private final String answer = "Hi";

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void before() throws IOException {
        in = tempFolder.newFile("in.txt");

        RandomAccessFile raf = new RandomAccessFile(in, "rw");
        raf.writeBytes(answer +  System.getProperty("line.separator"));
        raf.close();
    }

    /**
     * test ConsoleChat
     * when type and exit log everything
     * @throws IOException
     */
    @Test
    public void whenTypeAndExitThenLogEverything() throws IOException {
        File out = tempFolder.newFile("out.txt");
        String test1 = "test1";
        String stop = "stop";
        String test2 = "test2";
        String continueStr = "continue";
        String exit = "exit";
        StubInput input = new StubInput(new String[]{test1,stop, test2, continueStr, exit});
        ConsoleChat c = new ConsoleChat(in, out, input);
        List<String> actualLog = new ArrayList<>();
        c.run();
        RandomAccessFile raf = new RandomAccessFile(out, "r");
        String s;
        while((s = raf.readLine())!= null) {
            actualLog.add(s);
        }

        List<String> expectedLog = new ArrayList<>();
        expectedLog.add(test1);
        expectedLog.add(answer);
        expectedLog.add(stop);
        expectedLog.add(test2);
        expectedLog.add(continueStr);
        expectedLog.add(answer);
        expectedLog.add(exit);

        assertThat(actualLog, is(expectedLog));
    }

}