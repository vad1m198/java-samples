package ru.vmerkotan.server.actions;

import ru.vmerkotan.Action;
import ru.vmerkotan.PathWrapper;

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
     * DataOutputStream to write results to.
     */
    private DataOutputStream outputStream;
    /**
     * Holds current working dir path.
     */
    private PathWrapper workingDirPath;

    /**
     * Initiates new Action.
     *
     * @param key  String action key.
     * @param desc Action description.
     */
    public GetAction(String key, String desc, PathWrapper workingDirPath, DataOutputStream outputStream) {
        super(key, desc);
        this.workingDirPath = workingDirPath;
        this.outputStream = outputStream;
    }

    /**
     * Executes Get action.
     *
     * @param param Parameters to run action with.
     * @throws IOException when error appear
     */
    @Override
    public void execute(String param) throws IOException {
        if(Files.exists(Paths.get(workingDirPath.getPath().toString(), param)) && Files.isRegularFile(Paths.get(workingDirPath.getPath().toString(), param))) {
            byte[] bytes = Files.readAllBytes(Paths.get(workingDirPath.getPath().toString(), param));
            outputStream.writeUTF("200");
            outputStream.writeUTF(String.valueOf(Paths.get(workingDirPath.getPath().toString(), param).getFileName()));
            outputStream.writeUTF(new String(bytes, Charset.defaultCharset()));
            outputStream.writeUTF("File was transferred successfully");
        } else {
            outputStream.writeUTF("404");
            outputStream.writeUTF("Path does not exist or target is not a file");
        }

    }
}
