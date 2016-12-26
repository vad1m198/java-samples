package ru.vmerkotan.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Created by Вадим on 24.12.2016.
 */
public class Client {

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
                } else if("post".equals(systemInStr.split(" ")[0].trim()) && systemInStr.split(" ").length > 1) {
                    File fileToUpload = new File(systemInStr.split(" ")[1].trim());
                    if(fileToUpload.exists() && fileToUpload.isFile()) {
                        System.out.println("File exists");
                        out.writeUTF("200");
                        out.writeUTF(fileToUpload.getName());
                        out.writeLong(fileToUpload.length());
                        final int BYTE_ARRAY_SIZE = 4096;
                        byte[] arr = new byte[BYTE_ARRAY_SIZE];
                        try (ByteArrayOutputStream ous = new ByteArrayOutputStream();
                             FileInputStream fInput = new FileInputStream(fileToUpload)) {

                            long counter = 0;
                            long fileSize =  fileToUpload.length();
                            while ( counter <= fileSize) {
                                fInput.read(arr);
                                ous.write(arr, 0, (int) (fileSize - counter) > BYTE_ARRAY_SIZE ? BYTE_ARRAY_SIZE : (int) (fileSize - counter) );
                                counter += BYTE_ARRAY_SIZE;
                            }
                            out.write(ous.toByteArray());
                            out.flush();

                        }



                    } else {
                        System.out.println("File not found");
                        out.writeUTF("404");
                    }
                }
            }
        }
    }
}
