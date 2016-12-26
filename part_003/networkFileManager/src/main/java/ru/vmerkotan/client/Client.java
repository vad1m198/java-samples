package ru.vmerkotan.client;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Вадим on 24.12.2016.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        InetAddress add = InetAddress.getLocalHost();
        try(Socket s = new Socket(add, 5000)) {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();

            PrintWriter out = new PrintWriter(outStream, true);

            DataInputStream reader = new DataInputStream(inStream);
            Scanner systemIn = new Scanner(System.in);
            boolean done = false;
            while(!done){
                String inStr = reader.readUTF();
                System.out.println(inStr);
                String systemInStr = systemIn.nextLine();
                out.println(systemInStr);
                if("exit".trim().equalsIgnoreCase(systemInStr)) {
                    done = true;
                } else if("get".trim().equalsIgnoreCase(systemInStr.split(" ")[0])) {
                    if("200".equalsIgnoreCase(reader.readUTF())) {
                        String fileName = reader.readUTF();
                        long size = reader.readLong();
                        File f = new File( System.getProperty("java.io.tmpdir") +File.separator + fileName);
                        f.createNewFile();
                        final int BYTE_ARRAY_SIZE = 4096;
                        byte[] arr = new byte[BYTE_ARRAY_SIZE];

                        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))) {
                            long counter = 0;
                            while(counter <= size) {
                                reader.read(arr);
                                dos.write(arr, 0, (int) (size - counter) > BYTE_ARRAY_SIZE ? BYTE_ARRAY_SIZE : (int) (size - counter));
                                counter += BYTE_ARRAY_SIZE;
                            }
                            dos.flush();
                        }
                    }
                }
            }
        }
    }
}
