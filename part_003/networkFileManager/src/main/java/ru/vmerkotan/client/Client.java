package ru.vmerkotan.client;

import org.omg.CORBA.portable.*;
import ru.vmerkotan.FileManager;

import java.io.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Вадим on 24.12.2016.
 */
public class Client {

    private FileManager fileManager = new FileManager();

    InputStream in;
    PrintStream outputStream;

    public Client(InputStream in, PrintStream out) {
        this.in = in;
        this.outputStream = out;
    }

    public static void main(String[] args) throws IOException {
        Properties p = new Properties();
        p.load(new InputStreamReader(new FileInputStream(new File(".\\part_003\\networkFileManager\\app.properties"))));
        String host = p.getProperty("host");
        int port = Integer.valueOf(p.getProperty("port"));
        Client c = new Client(System.in, System.out);
        c.init(host, port);
    }

    public void init(String host, int port) throws IOException {


        try(Socket s = new Socket(host, port)) {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();

            DataOutputStream out = new DataOutputStream(outStream);
            DataInputStream reader = new DataInputStream(inStream);
            Scanner systemIn = new Scanner(in);
            boolean done = false;
            while(!done){
                String inStr = reader.readUTF();
                outputStream.println(inStr);
                String systemInStr = systemIn.nextLine();
                out.writeUTF(systemInStr);
                if("exit".equalsIgnoreCase(systemInStr.trim())) {
                    done = true;
                } else if("get".equals(systemInStr.split(" ")[0].trim()) && systemInStr.split(" ").length > 1) {
                    fileManager.readInputStreamToFile(reader, System.getProperty("java.io.tmpdir"));
                } else if("post".equals(systemInStr.split(" ")[0].trim()) && systemInStr.split(" ").length > 1) {
                    fileManager.writePathToOutputStream(systemInStr.split(" ")[1].trim(), out);
                }
            }
        }
    }
}
