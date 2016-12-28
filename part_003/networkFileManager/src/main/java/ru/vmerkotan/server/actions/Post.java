package ru.vmerkotan.server.actions;

import ru.vmerkotan.FileManager;
import ru.vmerkotan.Key;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by vmerkotan on 12/28/2016.
 */
public class Post extends Key {
    private FileManager fileManager = new FileManager();
    private String path;
    private DataInputStream inStream;

    public Post(String key, String description, String path, DataInputStream inStream) {
        super(key, description);
        this.path = path;
        this.inStream = inStream;
    }

    @Override
    public void execute() throws IOException {
        fileManager.readInputStreamToFile(inStream, path);
    }
}

