package ru.vmerkotan.server;

import ru.vmerkotan.FileManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Вадим on 24.12.2016.
 */
public class Server {

    static FileManager fileManager = new FileManager();

    public static void main(String[] args) throws IOException, InterruptedException {

        String currentDir = "C:\\";

        try(ServerSocket server = new ServerSocket(5000)) {
            try (Socket incoming = server.accept()) {
                System.out.println("Server accepted!!!");
                InputStream input = incoming.getInputStream();
                OutputStream output = incoming.getOutputStream();

                try (DataInputStream inStream = new DataInputStream(input)) {
                    DataOutputStream outputStr = new DataOutputStream(output);
                    outputStr.writeUTF("Hello! Enter EXIT to stop the program" + System.getProperty("line.separator") +
                                            "Some menu will go here:");
                    boolean done = false;
                    while(!done) {

                        String in = inStream.readUTF();
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
                            fileManager.writePathToOutputStream(currentDir + File.separator + in.split(" ")[1], outputStr);
                            Thread.sleep(10L);
                            outputStr.writeUTF("");
                        } else if ("post".equals(in.split(" ")[0].trim())){
                            fileManager.readInputStreamToFile(inStream,currentDir);
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
