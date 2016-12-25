package ru.vmerkotan.client;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
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
                }

                if("get".trim().equalsIgnoreCase(systemInStr.split(" ")[0])) {
                    if("exist".equalsIgnoreCase(reader.readUTF())) {
                        String fileName = reader.readUTF();
                        //System.out.println("File name " + fileName);
                        long size = reader.readLong();
                        System.out.println("File size: " + size);
                        File f = new File("C:" + File.separator + "test" + File.separator + "out"+File.separator + fileName);
                        f.createNewFile();
                        final int BYTE_ARRAY_SIZE = 4096;
                        byte[] arr = new byte[BYTE_ARRAY_SIZE];

                        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))) {
                            long counter = 0;
                            while(counter <= size) {
                                reader.read(arr);
                                dos.write(arr);
                                counter += BYTE_ARRAY_SIZE;
                            }
                        }
                    }
                }
            }
        }
    }
}
