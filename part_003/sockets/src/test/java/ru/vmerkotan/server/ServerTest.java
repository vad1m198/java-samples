
package ru.vmerkotan.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.Socket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Presents tests for Server class.
 * Created by vmerkotan on 12/26/2016.
 */
public class ServerTest {
    /**
     * holds root dir for tests.
     */
    private File rootDir;
    /**
     * port for tests.
     */
    private int port = 6543;
    /**
     * temp file name.
     */
    private final String tmpFileName = "tmp.txt";
    /**
     * temp file content.
     */
    private final String tmpFileContent = "String";
    /**
     * another temp file.
     */
    private final String tmp2FileName = "tmp2.txt";
    /**
     * temp folder name.
     */
    private final String tmpDirName = "tempDir";
    /**
     * DataOutputStream to work with.
     */
    private DataOutputStream out;
    /**
     * DataInputStream to read data from socketServer.
     */
    private DataInputStream reader;
    /**
     * File of tempDir folder.
     */
    private File tempDir;
    /**
     * Rule to hold temp folder.
     */
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * Runs Server instance in new thread.
     * Initiates variables.
     * @throws Exception when exception appear
     */
    @Before
    public void setUp() throws Exception {
        try {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Server s = new Server();

                        rootDir = tempFolder.newFolder("test");
                        File temp = new File(rootDir.getPath() + File.separator + tmpFileName);
                        tempDir = new File(rootDir.getPath() + File.separator + tmpDirName);
                        File temp2 = new File(rootDir.getPath() + File.separator + tmpDirName + File.separator + tmp2FileName);
                        temp.createNewFile();
                        RandomAccessFile raf = new RandomAccessFile(temp, "rw");
                        raf.writeBytes(tmpFileContent);
                        raf.close();
                        tempDir.mkdir();
                        temp2.createNewFile();
                        s.init(port, rootDir.getAbsolutePath());
                    } catch (Exception ex) {
                        System.out.println("Exception in setup method");
                        ex.printStackTrace();
                    }
                };
            }).start();
        } catch (Exception e) {
            System.out.println("Exception in setup method");
            e.printStackTrace();
        }

        Socket socket = new Socket(InetAddress.getLocalHost(), port);
        InputStream inStream = socket.getInputStream();
        OutputStream outStream = socket.getOutputStream();

        out = new DataOutputStream(outStream);
        reader = new DataInputStream(inStream);

    }

    /**
     * clears tempFolder.
     */
    @After
    public void tearDown() {
        tempFolder.delete();
    }

    /**
     * test ls key.
     * @throws IOException when exception appear
     */
    @Test
    public void whenLSCommandThenReturnListAllFilesAndDirs() throws IOException {

        reader.readUTF();
        out.writeUTF("ls");
        String result = reader.readUTF();
        out.writeUTF("exit");
        String expected = "";
        for (File f : rootDir.listFiles()) {
            expected += f.getName() + System.getProperty("line.separator");
        }
        assertThat(result, is(expected));
    }

    /**
     * test cd key.
     * @throws IOException when exception appear
     */
    @Test
    public void whenCDCommandThenMoveToDir() throws IOException {

        reader.readUTF();
        out.writeUTF("cd " + tmpDirName);
        reader.readUTF();
        out.writeUTF("ls");
        String result = reader.readUTF();
        out.writeUTF("exit");
        String expected = "";
        for (File f : tempDir.listFiles()) {
            expected += f.getName() + System.getProperty("line.separator");
        }
        assertThat(result, is(expected));
    }

    /**
     * test cd key.
     * @throws IOException when exception appear
     */
    @Test
    public void whenCDToParentCommandThenMoveToParentDir() throws IOException {

        reader.readUTF();
        out.writeUTF("cd " + tmpDirName);
        reader.readUTF();
        out.writeUTF("cd ..");
        reader.readUTF();
        out.writeUTF("ls");
        String result = reader.readUTF();
        out.writeUTF("exit");

        String expected = "";
        for (File f : rootDir.listFiles()) {
            expected += f.getName() + System.getProperty("line.separator");
        }
        assertThat(result, is(expected));
    }


    /**
     * test get key.
     * @throws IOException when exception appear
     */
    @Test
    public void whenGETCommandThenFileInTempDir() throws IOException {
        reader.readUTF();
        out.writeUTF("get " + tmpFileName);
        String status = reader.readUTF();
        String fileName = reader.readUTF();
        String content = reader.readUTF();
        out.writeUTF("exit");
        assertThat("200", is(status));
        assertThat(tmpFileName, is(fileName));
        assertThat(tmpFileContent, is(content));
    }

    /**
     * test get key.
     * @throws IOException when exception appear
     */
    @Test
    public void whenGETCommandForUnExistingThen404Response() throws IOException {
        reader.readUTF();
        out.writeUTF("get 123654879.asda");
        String status = reader.readUTF();
        reader.readUTF();
        out.writeUTF("exit");
        assertThat("404", is(status));
    }

    /**
     * test post key.
     * @throws IOException when exception appear
     */
    @Test
    public void whenPOSTCommandThenFileInCurrentDir() throws IOException {
        String testStr = "test String";
        File tmpFile = new File(rootDir.getPath() + File.separator + "post.txt");
        tmpFile.createNewFile();
        RandomAccessFile raf = new RandomAccessFile(tmpFile, "rw");
        raf.writeBytes(testStr);
        raf.close();

        reader.readUTF();
        out.writeUTF("cd " + tmpDirName);
        reader.readUTF();
        out.writeUTF("post " + tmpFile.getAbsolutePath());
        out.writeUTF(tmpFile.getName());
        out.writeUTF(testStr);
        reader.readUTF();
        out.writeUTF("ls");
        String result = reader.readUTF();
        out.writeUTF("exit");
        String expected = "";
        for (File f : tempDir.listFiles()) {
            expected += f.getName() + System.getProperty("line.separator");
        }
        assertThat(expected, is(result));
    }
}