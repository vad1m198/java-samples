package ru.vmerkotan.server;

import ru.vmerkotan.FileManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by Вадим on 24.12.2016.
 */
public class Server {

    private FileManager fileManager = new FileManager();

    public static void main(String[] args) throws IOException, InterruptedException {
        Properties p = new Properties();
        p.load(new InputStreamReader(new FileInputStream(new File(".\\part_003\\networkFileManager\\app.properties"))));
        int port = Integer.valueOf(p.getProperty("port"));
        String currentDir = p.getProperty("rootDir");
        Server s = new Server();
        s.init(currentDir, port);
    }


    public void init(String rootDir, int port) throws IOException, InterruptedException {
        String currentDir = rootDir;

        try(ServerSocket server = new ServerSocket(port)) {
            try (Socket incoming = server.accept()) {
                System.out.println("Server accepted!!!");
                InputStream input = incoming.getInputStream();
                OutputStream output = incoming.getOutputStream();

                try (DataInputStream inStream = new DataInputStream(input)) {
                    DataOutputStream outputStr = new DataOutputStream(output);
                    outputStr.writeUTF("Menu:" + System.getProperty("line.separator") +
                            "ls - lists all files/folders in current folder" + System.getProperty("line.separator") +
                            "cd 'path' - changes current folder to specified relative path. Type '..' to move to parent folder" + System.getProperty("line.separator") +
                            "get 'fileName' - downloads specified fileName to temp folder. Current folder should be the folder you want to download file from" + System.getProperty("line.separator") +
                            "post 'path' - uploads specified path to current folder" + System.getProperty("line.separator") +
                            "exit - stops the program" + System.getProperty("line.separator"));
                    boolean done = false;
                    while(!done) {
//                        System.out.println("In while loop");
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
                        } else if ("post".equals(in.split(" ")[0].trim()) && in.split(" ").length > 1){
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