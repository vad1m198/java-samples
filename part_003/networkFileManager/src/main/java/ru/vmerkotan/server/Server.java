package ru.vmerkotan.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Вадим on 24.12.2016.
 */
public class Server {

    public static void main(String[] args) throws IOException {

        String currentDir = "C:";

        try(ServerSocket server = new ServerSocket(5000)) {
            try (Socket incoming = server.accept()) {
                System.out.println("Server accepted!!!");
                InputStream input = incoming.getInputStream();
                OutputStream output = incoming.getOutputStream();

                try (Scanner scanner = new Scanner(input)) {
                    DataOutputStream outputStr = new DataOutputStream(output);
                    outputStr.writeUTF("Hello! Enter EXIT to stop the programm" + System.getProperty("line.separator") +
                                            "Some menu will go here:");
                    boolean done = false;
                    while(!done && scanner.hasNextLine()) {
                        String in = scanner.nextLine();
                        if("ls".equals(in.trim())) {
                            File file = new File(currentDir);
                            if(file.exists() && file.isDirectory()) {
                                StringBuilder sb = new StringBuilder();
                                for(String s: file.list()) {
                                    System.out.println(s);
                                    sb.append(s + System.getProperty("line.separator"));
                                }
                                outputStr.writeUTF(sb.toString());
                                outputStr.flush();
                            }
                        }

                        if("cd".equals(in.split(" ")[0]) && in.split(" ").length > 1) {
                            String childPath = in.split(" ")[1];

                            if("..".equals(childPath)) {
                                File tmp = new File(currentDir).getParentFile();
                                if(tmp != null && tmp.exists()) {
                                    currentDir = tmp.getPath();
                                }
                            } else {
                                File file = new File(currentDir + File.separator + childPath);
                                if(file != null && file.exists() && file.isDirectory()) {
                                    currentDir = currentDir + File.separator + childPath;
                                }
                            }
                            outputStr.writeUTF("");
                        }

                        if("get".equals(in.split(" ")[0]) && in.split(" ").length > 1) {
                            String fileName = in.split(" ")[1];
                            File file = new File(currentDir + File.separator + fileName);
                            if(file != null && file.exists() && file.isFile()) {
                                outputStr.writeUTF("exist");
                                //write file name
                                outputStr.writeUTF(file.getName());
                                //write size
                                outputStr.writeLong(file.length());
                                final int BYTE_ARRAY_SIZE = 4096;
                                byte[] arr = new byte[BYTE_ARRAY_SIZE];
                                try (ByteArrayOutputStream ous = new ByteArrayOutputStream();
                                     FileInputStream fInput = new FileInputStream(file)) {
                                    int availableBytes = -1;
                                    /*while (( availableBytes = fInput.available()) > 0) {
                                        System.out.println("availableBytes" + availableBytes);
                                        fInput.read(arr);
                                        System.out.println("write bytes");
                                        ous.write(arr,0, availableBytes > BYTE_ARRAY_SIZE ? BYTE_ARRAY_SIZE : availableBytes);
                                        System.out.println("write bytes 2");
                                    }*/
                                    while ( fInput.available() > 0) {
                                        System.out.println(fInput.available());
                                        fInput.read(arr);
                                        ous.write(arr);
                                    }
                                    outputStr.write(ous.toByteArray());
                                }
                            }
                            outputStr.writeUTF("");
                        }

                        if("exit".equalsIgnoreCase(in.trim())) {
                            done = true;
                        }
                    }
                    incoming.close();
                }

            }
        }
    }
}
