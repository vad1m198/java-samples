package ru.vmerkotan.client;

import ru.vmerkotan.FileManager;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Created by Вадим on 24.12.2016.
 */
public class Client {

    static FileManager fileManager = new FileManager();

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
                    if("200".equalsIgnoreCase(reader.readUTF())) {
                        String fileName = reader.readUTF();
                        long size = reader.readLong();
                        File f = new File( System.getProperty("java.io.tmpdir") +File.separator + fileName);
                        f.createNewFile();
                        RandomAccessFile raf = new RandomAccessFile(f, "rw");
                        raf.setLength(size);
                        raf.close();
                        fileManager.readFromStreamToFile(reader, f);
                    }
                } else if("post".equals(systemInStr.split(" ")[0].trim()) && systemInStr.split(" ").length > 1) {
                    File fileToUpload = new File(systemInStr.split(" ")[1].trim());
                    if(fileToUpload.exists() && fileToUpload.isFile()) {
                        out.writeUTF("200");
                        out.writeUTF(fileToUpload.getName());
                        out.writeLong(fileToUpload.length());
                        fileManager.readFromFileToStream(fileToUpload, out);
                    } else {
                        out.writeUTF("404");
                    }
                }
            }
        }
    }
}
