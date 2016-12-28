package ru.vmerkotan;

import java.io.IOException;

/**
 * Created by vmerkotan on 12/28/2016.
 */
public class Key implements IKey {
    String key;
    String description;
    public Key(String key, String description) {
        this.key = key;
        this.description = description;
    }
    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void execute() throws IOException, InterruptedException {

    }
}
