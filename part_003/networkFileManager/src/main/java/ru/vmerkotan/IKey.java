package ru.vmerkotan;

import java.io.IOException;

/**
 * Created by vmerkotan on 12/28/2016.
 */
public interface IKey {

    String getKey();

    String getDescription();

    void execute() throws IOException, InterruptedException;
}
