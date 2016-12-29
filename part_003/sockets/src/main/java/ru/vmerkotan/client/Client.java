package ru.vmerkotan.client;

import ru.vmerkotan.client.actions.PostAction;
import ru.vmerkotan.manager.ActionsManager;
import ru.vmerkotan.client.actions.ChangeDirAction;
import ru.vmerkotan.client.actions.GetAction;
import ru.vmerkotan.client.actions.ListAction;
import ru.vmerkotan.manager.InvalidActionKeyException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

/**
 * Class Client presents a client to work with sockets.
 * Created by Вадим on 24.12.2016.
 */
public class Client {
    /**
     * Actions manager instance.
     */
    private ActionsManager manager = new ActionsManager();

    /**
     * Main method to start Client.
     * @param args  args
     * @throws IOException when error appear.
     */
    public static void main(String[] args) throws IOException {
        Properties p = new Properties();
        p.load(new InputStreamReader(new FileInputStream(new File(".\\part_003\\sockets\\app.properties"))));
        String host = p.getProperty("host");
        int port = Integer.valueOf(p.getProperty("port"));
        Client c = new Client();
        c.init(host, port, System.getProperty("java.io.tmpdir"));
    }

    /**
     * Init new Client.
     * @param host  host address
     * @param port  port to start Socket on.
     * @param tempDir  String path to tempDir to store files.
     * @throws IOException throws when appear.
     */
    private void init(String host, int port, String tempDir) throws IOException {
        try (Socket s = new Socket(host, port)) {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();

            DataOutputStream out = new DataOutputStream(outStream);
            DataInputStream reader = new DataInputStream(inStream);
            manager.addAction(new ListAction("ls", "list Files", out));
            manager.addAction(new ChangeDirAction("cd", "change dir action Files", out));
            manager.addAction(new GetAction("get", "get file from server", tempDir, reader, out));
            manager.addAction(new PostAction("post", "post file to server", out));
            Scanner systemIn = new Scanner(System.in);
            boolean done = false;
            while (!done) {
                String inStr = reader.readUTF();
                System.out.println("incoming message >>>>> " + System.getProperty("line.separator") + inStr);
                String systemInStr = systemIn.nextLine();

                try {
                    manager.executeByKey(systemInStr.trim());
                } catch (InvalidActionKeyException rte) {
//                    System.out.println(rte.getMessage());
                    out.writeUTF(systemInStr.trim());
                }
                if ("exit".equalsIgnoreCase(systemInStr.trim())) {
                    done = true;
                }
            }
        }
    }
}