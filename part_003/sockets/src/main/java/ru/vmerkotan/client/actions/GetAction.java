package ru.vmerkotan.client.actions;

import ru.vmerkotan.Action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Get class represents a get file from Action.
 * Created by vmerkotan on 12/29/2016.
 */
public class GetAction extends Action {
    /**
     * DataInoutStream to read from.
     */
    private DataInputStream inputStream;
    /**
     * DataOutputStream to write results to.
     */
    private DataOutputStream outputStream;
    /**
     * Holds path to save files to.
     */
    private String outputPath;
    /**
     * Initiates new Action.
     *
     * @param key  String action key.
     * @param desc Action description.
     * @param outputPath outputPath.
     * @param inputStream  DataInputStream to read data from.
     * @param outputStream DataOutputStream to write data to.
     */
    public GetAction(String key, String desc, String outputPath, DataInputStream inputStream, DataOutputStream outputStream) {
        super(key, desc);
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.outputPath = outputPath;
    }

    /**
     * Executes Get action.
     *
     * @param param Parameters to run action with.
     * @throws IOException when error appear
     */
    @Override
    public void execute(String param) throws IOException {
       outputStream.writeUTF(getKey() + " " + param);
       if ("200".equals(inputStream.readUTF())) {
           String fileName = inputStream.readUTF();
           String content = inputStream.readUTF();
           Files.createFile(Paths.get(outputPath, fileName));
           Files.write(Paths.get(outputPath, fileName), content.getBytes(Charset.defaultCharset()));
       }
    }
}
