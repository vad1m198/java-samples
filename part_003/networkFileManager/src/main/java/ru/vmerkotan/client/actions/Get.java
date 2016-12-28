package ru.vmerkotan.client.actions;

import ru.vmerkotan.FileManager;
import ru.vmerkotan.Key;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by vmerkotan on 12/28/2016.
 */
public class Get extends Key {

    private FileManager fileManager = new FileManager();
    DataInputStream inStream;

    public Get(String key, String description, DataInputStream inStream) {
        super(key, description);
        this.inStream = inStream;
    }

    @Override
    public void execute() throws IOException {
        fileManager.readInputStreamToFile(inStream, System.getProperty("java.io.tmpdir"));
    }
}
