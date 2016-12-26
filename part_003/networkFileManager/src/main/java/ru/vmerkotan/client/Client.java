package ru.vmerkotan.client;

import ru.vmerkotan.FileManager;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Created by Вадим on 24.12.2016.
 */
public class Client {

    private static FileManager fileManager = new FileManager();

    public static void main(String[] args) throws IOException {
        InetAddress add = InetAddress.getLocalHost();
        try(Socket s = new Socket(add, 5000)) {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();

            DataOutputStream out = new DataOutputStream(outStream);
            DataInputStream reader = new DataInputStream(inStream);
            Scanner systemIn = new Scanner(System.in);
            boolean done = false;
            while(!done){
                String inStr = reader.readUTF();
                System.out.println(inStr);
                String systemInStr = systemIn.nextLine();
                out.writeUTF(systemInStr);
                if("exit".equalsIgnoreCase(systemInStr.trim())) {
                    done = true;
                } else if("get".equals(systemInStr.split(" ")[0].trim())) {
                    fileManager.readInputStreamToFile(reader, System.getProperty("java.io.tmpdir"));
                } else if("post".equals(systemInStr.split(" ")[0].trim()) && systemInStr.split(" ").length > 1) {
                    fileManager.writePathToOutputStream(systemInStr.split(" ")[1].trim(), out);
                }
            }
        }
    }
}
