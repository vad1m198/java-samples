package ru.vmerkotan.server.actions;

import ru.vmerkotan.FileManager;
import ru.vmerkotan.Key;

import java.io.DataOutputStream;

import java.io.IOException;

/**
 * Created by vmerkotan on 12/28/2016.
 */
public class Get extends Key {
    private FileManager fileManager = new FileManager();

    DataOutputStream outStream;
    String path;

    public Get(String key, String description, DataOutputStream outStream, String path) {
        super(key, description);
        this.outStream = outStream;
    }

    @Override
    public void execute() throws InterruptedException, IOException {
        fileManager.writePathToOutputStream(path, outStream);
        Thread.sleep(10L);

    }
}
