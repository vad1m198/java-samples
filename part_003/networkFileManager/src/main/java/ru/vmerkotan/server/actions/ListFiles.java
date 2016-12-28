package ru.vmerkotan.server.actions;

import ru.vmerkotan.Key;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by vmerkotan on 12/28/2016.
 */
public class ListFiles extends Key {

    String currentDir;
    String newLocation;
    DataOutputStream outputStr;

    public ListFiles(String key, String description, String currentDir, String newLocation, DataOutputStream outputStr) {
        super(key, description);
        this.currentDir = currentDir;
        this.newLocation = newLocation;
        this.outputStr = outputStr;
    }

    @Override
    public void execute() throws IOException {
        File file = new File(currentDir);
        if (file.exists() && file.isDirectory()) {
            StringBuilder sb = new StringBuilder();
            for (String s: file.list()) {
                sb.append(s + System.getProperty("line.separator"));
            }
            outputStr.writeUTF(sb.toString());
            outputStr.flush();
        }
    }
}
