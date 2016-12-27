package ru.vmerkotan.client;

import ru.vmerkotan.FileManager;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

/**
 * Class Client presents a client to work with sockets.
 * Created by Вадим on 24.12.2016.
 */
public class Client {
    /**
     * Holds FileManager instance to call service methods.
     */
    private FileManager fileManager = new FileManager();
    /**
     * to read from.
     */
    private InputStream in;
    /**
     * to write to.
     */
    private PrintStream outputStream;

    /**
     * Creates new Client instance.
     * @param in    InputStream to read data from.
     * @param out   PrintStream to print data to.
     */
    public Client(InputStream in, PrintStream out) {
        this.in = in;
        this.outputStream = out;
    }

    /**
     * Main method to start Client.
     * @param args  args
     * @throws IOException when error appear.
     */
    public static void main(String[] args) throws IOException {
        Properties p = new Properties();
        p.load(new InputStreamReader(new FileInputStream(new File(".\\part_003\\networkFileManager\\app.properties"))));
        String host = p.getProperty("host");
        int port = Integer.valueOf(p.getProperty("port"));
        Client c = new Client(System.in, System.out);
        c.init(host, port);
    }

    /**
     * Inits new Client.
     * @param host  host address
     * @param port  port
     * @throws IOException throws when appear.
     */
    public void init(String host, int port) throws IOException {
        try (Socket s = new Socket(host, port)) {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();

            DataOutputStream out = new DataOutputStream(outStream);
            DataInputStream reader = new DataInputStream(inStream);
            Scanner systemIn = new Scanner(in);
            boolean done = false;
            while (!done) {
                String inStr = reader.readUTF();
                outputStream.println(inStr);
                String systemInStr = systemIn.nextLine();
                out.writeUTF(systemInStr);
                if ("exit".equalsIgnoreCase(systemInStr.trim())) {
                    done = true;
                } else if ("get".equals(systemInStr.split(" ")[0].trim()) && systemInStr.split(" ").length > 1) {
                    fileManager.readInputStreamToFile(reader, System.getProperty("java.io.tmpdir"));
                } else if ("post".equals(systemInStr.split(" ")[0].trim()) && systemInStr.split(" ").length > 1) {
                    fileManager.writePathToOutputStream(systemInStr.split(" ")[1].trim(), out);
                }
            }
        }
    }
}
