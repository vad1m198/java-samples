package ru.vmerkotan.server;

import ru.vmerkotan.PathWrapper;
import ru.vmerkotan.manager.ActionsManager;
import ru.vmerkotan.manager.InvalidActionKeyException;
import ru.vmerkotan.server.actions.ChangeDirAction;
import ru.vmerkotan.server.actions.GetAction;
import ru.vmerkotan.server.actions.ListAction;
import ru.vmerkotan.server.actions.PostAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Server class presents Socket Server instance.
 * Created by Вадим on 24.12.2016.
 */
public class Server {
    /**
     * Actions manager instance.
     */
    private ActionsManager manager = new ActionsManager();

    /**
     * Runs Server instance.
     * @param args  args
     * @throws IOException  when appear
     * @throws InterruptedException  when appear
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Properties p = new Properties();
        p.load(new InputStreamReader(new FileInputStream(new File(".\\part_003\\sockets\\app.properties"))));
        int port = Integer.valueOf(p.getProperty("port"));
        String currentDir = p.getProperty("rootDir");
        Server s = new Server();
        s.init(port, currentDir);
    }

    /**
     * initiates new Server instance.
     * @param port      Post to start server socket.
     * @param workDir   Path to folder to start app.
     * @throws IOException  when appear
     */
    void init(int port, String workDir) throws IOException {
        PathWrapper wrapper = new PathWrapper(Paths.get(workDir));
        try (ServerSocket server = new ServerSocket(port)) {
            try (Socket incoming = server.accept()) {
                System.out.println("Server accepted!!!");
                InputStream input = incoming.getInputStream();
                OutputStream output = incoming.getOutputStream();

                try (DataInputStream inStream = new DataInputStream(input)) {
                    DataOutputStream outputStr = new DataOutputStream(output);
                    manager.addAction(new ListAction("ls", "lists all files and folders form current folder", wrapper, outputStr));
                    manager.addAction(new ChangeDirAction("cd", "change folder", wrapper, outputStr));
                    manager.addAction(new GetAction("get", "download specified file to local folder", wrapper, outputStr));
                    manager.addAction(new PostAction("post", "upload file to current folder", wrapper, outputStr, inStream));
                    boolean done = false;
                    outputStr.writeUTF(manager.getKeysDescription());
                    while (!done) {
                        String in = inStream.readUTF();
                        try {
                            manager.executeByKey(in);
                        } catch (InvalidActionKeyException rte) {
                            outputStr.writeUTF(rte.getMessage());
                        }
                        if ("exit".equalsIgnoreCase(in)) {
                            done = true;
                        }
                    }
                    incoming.close();
                }
            }
        }
    }
}