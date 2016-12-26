package ru.vmerkotan.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
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

    @Before
    public void setUp() throws Exception{
        try {
            new Thread(new Runnable(){
                public void run() {
                    try{
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
                        s.init(rootDir.getAbsolutePath(), port);
                    }catch(Exception ex){
                        System.out.println("Exception in setup method");
                        ex.printStackTrace();
                    }
                };
            }).start();
        } catch (Exception e) {
            System.out.println("Exception in setup method");
            e.printStackTrace();
        }

        Socket socket = new Socket(InetAddress.getLocalHost(), 6543);
        InputStream inStream = socket.getInputStream();
        OutputStream outStream = socket.getOutputStream();

        out = new DataOutputStream(outStream);
        reader = new DataInputStream(inStream);

    }

    @After
    public void tearDown() {
        tempFolder.delete();
    }

    /**
     * test ls key.
     * @throws IOException when exception appear
     * @throws InterruptedException when exception appear
     */
    @Test
    public void whenLSCommandThenReturnListAllFilesAndDirs() throws IOException, InterruptedException {

        reader.readUTF();
        out.writeUTF("ls");
        String result = reader.readUTF();
        out.writeUTF("exit");
        String expected = "";
        for(File f : rootDir.listFiles()) {
            expected += f.getName() + System.getProperty("line.separator");
        }
        assertThat(result, is(expected));
    }

    /**
     * test cd key.
     * @throws IOException when exception appear
     * @throws InterruptedException when exception appear
     */
    @Test
    public void whenCDCommandThenMoveToDir() throws IOException, InterruptedException {

        reader.readUTF();
        out.writeUTF("cd " + tmpDirName);
        reader.readUTF();
        out.writeUTF("ls");
        String result = reader.readUTF();
        out.writeUTF("exit");
        String expected = "";
        for(File f : tempDir.listFiles()) {
            expected += f.getName() + System.getProperty("line.separator");
        }
        assertThat(result, is(expected));
    }

    /**
     * test cd key.
     * @throws IOException when exception appear
     * @throws InterruptedException when exception appear
     */
    @Test
    public void whenCDToParentCommandThenMoveToParentDir() throws IOException, InterruptedException {

        reader.readUTF();
        out.writeUTF("cd " + tmpDirName);
        reader.readUTF();
        out.writeUTF("cd ..");
        reader.readUTF();
        out.writeUTF("ls");
        String result = reader.readUTF();
        out.writeUTF("exit");

        String expected = "";
        for(File f : rootDir.listFiles()) {
            expected += f.getName() + System.getProperty("line.separator");
        }
        assertThat(result, is(expected));
    }


    /**
     * test get key.
     * @throws IOException when exception appear
     * @throws InterruptedException when exception appear
     */
    @Test
    public void whenGETCommandThenFileInTempDir() throws IOException, InterruptedException {
        byte[] b;
        reader.readUTF();
        out.writeUTF("get " + tmpFileName);
        String status = reader.readUTF();
        String fileName = reader.readUTF();
        b = new byte[(int) reader.readLong()];
        reader.readFully(b);
        reader.readUTF();
        out.writeUTF("exit");
        assertThat("200", is(status));
        assertThat(tmpFileName, is(fileName));
        assertThat(tmpFileContent, is(new String(b)));
    }

    /**
     * test post key.
     * @throws IOException when exception appear
     * @throws InterruptedException when exception appear
     */
    @Test
    public void whenPOSTCommandThenFileInCurrentDir() throws IOException, InterruptedException {
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
        out.writeUTF("200");
        out.writeUTF(tmpFile.getName());
        out.writeLong(tmpFile.length());
        out.write(testStr.getBytes());
        reader.readUTF();
        out.writeUTF("ls");
        String result = reader.readUTF();
        out.writeUTF("exit");
        String expected = "";
        for(File f : tempDir.listFiles()) {
            expected += f.getName() + System.getProperty("line.separator");
        }
        assertThat(expected, is(result));
    }
}
