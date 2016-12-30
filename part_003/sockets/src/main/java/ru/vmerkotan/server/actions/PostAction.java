package ru.vmerkotan.server.actions;

import ru.vmerkotan.Action;
import ru.vmerkotan.PathWrapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * PostAction class represents upload to server action.
 * Created by vmerkotan on 12/29/2016.
 */
public class PostAction extends Action {
    /**
     * DataOutputStream to write results to.
     */
    private DataOutputStream outputStream;
    /**
     * DataInputStream to read from.
     */
    private DataInputStream inputStream;
    /**
     * Holds current working dir path.
     */
    private PathWrapper workingDirPath;

    /**
     * Initiates new Action.
     *
     * @param key  String action key.
     * @param desc Action description.
     * @param workingDirPath path to working dir.
     * @param outputStream DataOutputStream to write data to.
     * @param inputStream DataInputStream to read data from.
     */
    public PostAction(String key, String desc, PathWrapper workingDirPath, DataOutputStream outputStream, DataInputStream inputStream) {
        super(key, desc);
        this.workingDirPath = workingDirPath;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    /**
     * executes action.
     * @param param Parameters to run action with.
     * @throws IOException when error appear.
     */
    @Override
    public void execute(String param) throws IOException {
        String fileName = inputStream.readUTF();
        String content = inputStream.readUTF();
        Files.createFile(Paths.get(workingDirPath.getPath().toString(), fileName));
        Files.write(Paths.get(workingDirPath.getPath().toString(), fileName), content.getBytes(Charset.defaultCharset()));
        outputStream.writeUTF("File receives successfully");
    }
}
