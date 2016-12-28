package ru.vmerkotan.client.actions;

import ru.vmerkotan.FileManager;
import ru.vmerkotan.Key;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by vmerkotan on 12/28/2016.
 */
public class Post extends Key {
    private FileManager fileManager = new FileManager();
    private DataOutputStream outStream;
    private String path;
    public Post(String key, String description, DataOutputStream outStream, String path) {
        super(key, description);
        this.outStream = outStream;
        this.path = path;

    }

    @Override
    public void execute() throws IOException {
        fileManager.writePathToOutputStream(this.path, this.outStream);
    }
}
