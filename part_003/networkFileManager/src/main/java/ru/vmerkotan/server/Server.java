package ru.vmerkotan.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Вадим on 24.12.2016.
 */
public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {

        String currentDir = "C:\\";

        try(ServerSocket server = new ServerSocket(5000)) {
            try (Socket incoming = server.accept()) {
                System.out.println("Server accepted!!!");
                InputStream input = incoming.getInputStream();
                OutputStream output = incoming.getOutputStream();

                try (DataInputStream scanner = new DataInputStream(input)) {
                    DataOutputStream outputStr = new DataOutputStream(output);
                    outputStr.writeUTF("Hello! Enter EXIT to stop the program" + System.getProperty("line.separator") +
                                            "Some menu will go here:");
                    boolean done = false;
                    while(!done) {

                        String in = scanner.readUTF();
                        if("ls".equals(in.trim())) {
                            File file = new File(currentDir);
                            if(file.exists() && file.isDirectory()) {
                                StringBuilder sb = new StringBuilder();
                                for(String s: file.list()) {
                                    sb.append(s + System.getProperty("line.separator"));
                                }
                                outputStr.writeUTF(sb.toString());
                                outputStr.flush();
                            }
                        } else if("cd".equals(in.split(" ")[0]) && in.split(" ").length > 1) {
                            String childPath = in.split(" ")[1];

                            if("..".equals(childPath)) {
                                File tmp = new File(currentDir).getParentFile();
                                if(tmp != null && tmp.exists()) {
                                    currentDir = tmp.getPath();
                                }
                            } else {
                                File file = new File(currentDir + File.separator + childPath);
                                if(file.exists() && file.isDirectory()) {
                                    currentDir = currentDir + File.separator + childPath;
                                }
                            }
                            outputStr.writeUTF("");
                        } else if("get".equals(in.split(" ")[0]) && in.split(" ").length > 1) {
                            String fileName = in.split(" ")[1];
                            File file = new File(currentDir + File.separator + fileName);
                            if( file.exists() && file.isFile()) {
                                outputStr.writeUTF("200");
                                //write file name
                                outputStr.writeUTF(file.getName());
                                //write size
                                outputStr.writeLong(file.length());
                                final int BYTE_ARRAY_SIZE = 4096;
                                byte[] arr = new byte[BYTE_ARRAY_SIZE];
                                try (ByteArrayOutputStream ous = new ByteArrayOutputStream();
                                     FileInputStream fInput = new FileInputStream(file)) {

                                    long counter = 0;
                                    long fileSize =  file.length();
                                    while ( counter <= fileSize) {
                                        fInput.read(arr);
                                        ous.write(arr, 0, (int) (fileSize - counter) > BYTE_ARRAY_SIZE ? BYTE_ARRAY_SIZE : (int) (fileSize - counter) );
                                        counter += BYTE_ARRAY_SIZE;
                                    }
                                    outputStr.write(ous.toByteArray());
                                    outputStr.flush();
                                }
                            } else {
                                outputStr.writeUTF("404");
                            }
                            Thread.sleep(10L);
                            outputStr.writeUTF("");
                        } else if ("post".equals(in.split(" ")[0].trim())){
                            System.out.println("Post is present");
                            if("200".equalsIgnoreCase(scanner.readUTF())) {
                                System.out.println("File exist");
                                String fileName = scanner.readUTF();
                                long fileSize = scanner.readLong();
                                System.out.println(fileName + ":" + fileSize);
                            } else {
                                System.out.println("File not found");
                            }
                            outputStr.writeUTF("");
                        } else if("exit".equalsIgnoreCase(in.trim())) {
                            done = true;
                        } else {
                            outputStr.writeUTF("");
                        }
                    }
                    incoming.close();
                }

            }
        }
    }
}
