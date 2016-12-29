package ru.vmerkotan.client.actions;

import ru.vmerkotan.Action;
import ru.vmerkotan.PathWrapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Action to list files and folders names from working folder.
 * Created by vmerkotan on 12/29/2016.
 */
public class ListAction extends Action {
    /**
     * DataOutputStream to write results to.
     */
    private DataOutputStream outputStream;
    /**
     * Constructs new ListAction object.
     * @param key             String action key.
     * @param desc            Action description.
     * @param outputStream    DataOutputStream to write results to.
     */
    public ListAction(String key, String desc, DataOutputStream outputStream) {
        super(key, desc);
        this.outputStream = outputStream;
    }

    /**
     * Writes all file names of working folder
     * to outputStream.
     * @param param Parameters to run action with.
     * @throws IOException  when exception appear.
     */
    @Override
    public void execute(String param) throws IOException {
        outputStream.writeUTF(getKey() + " " + param);
    }
}
