package ru.vmerkotan.client.actions;

import ru.vmerkotan.Action;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * PostAction class represents Action to upload files to server.
 * Created by vmerkotan on 12/29/2016.
 */
public class PostAction extends Action {
    /**
     * DataOutputStream to write to.
     */
    private DataOutputStream outputStream;
    /**
     * Initiates new Action.
     *
     * @param key           String action key.
     * @param desc          Action description.
     * @param outputStream  Action description.
     */
    public PostAction(String key, String desc, DataOutputStream outputStream) {
        super(key, desc);
        this.outputStream = outputStream;
    }

    /**
     * Executes post Action
     * @param param Parameters to run action with.
     * @throws IOException  when exception appear.
     */
    @Override
    public void execute(String param) throws IOException {
        if(Paths.get(param).isAbsolute() && Files.exists(Paths.get(param))) {
            outputStream.writeUTF(getKey());
            outputStream.writeUTF(String.valueOf(Paths.get(param).getFileName()));
            byte[] bytes = Files.readAllBytes(Paths.get(param));
            outputStream.writeUTF(new String(bytes, Charset.defaultCharset()));
        }
    }
}
