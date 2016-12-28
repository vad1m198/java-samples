package ru.vmerkotan.server.actions;

import ru.vmerkotan.Key;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

/**
 * Created by vmerkotan on 12/28/2016.
 */
public class ChangeDirectory extends Key {

    DataOutputStream outStream;
    String currentDir;
    String path;

    public ChangeDirectory(String key, String description, String currentDir, String path) {
        super(key, description);
        this.currentDir = currentDir;
        this.path = path;
    }

    @Override
    public void execute() {
        String childPath = path;

        if ("..".equals(childPath)) {
            File tmp = new File(currentDir).getParentFile();
            if (tmp != null && tmp.exists()) {
                currentDir = tmp.getPath();
            }
        } else {
            File file = new File(currentDir + File.separator + childPath);
            if (file.exists() && file.isDirectory()) {
                currentDir = currentDir + File.separator + childPath;
            }
        }
    }
}
