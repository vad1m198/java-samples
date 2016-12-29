package ru.vmerkotan.server.actions;

import ru.vmerkotan.Action;
import ru.vmerkotan.PathWrapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Action to change working folder.
 * Created by vmerkotan on 12/29/2016.
 */
public class ChangeDirAction extends Action {
    /**
     * Holds current working dir PathWrapper.
     */
    private PathWrapper workingDirPath;
    /**
     * DataOutputStream to write results to.
     */
    private DataOutputStream outputStream;
    /**
     * Constructs new ChangeDirAction object.
     *
     * @param key             String action key.
     * @param desc            Action description.
     * @param workingDirPath  Path to working folder.
     * @param outputStream    DataOutputStream to write results to.
     */
    public ChangeDirAction(String key, String desc, PathWrapper workingDirPath, DataOutputStream outputStream) {
        super(key, desc);
        this.workingDirPath = workingDirPath;
        this.outputStream = outputStream;
    }

    /**
     * Changes working dir.
     *
     * @throws IOException when exception appear.
     */
    @Override
    public void execute(String param) throws IOException {
        System.out.println("Executing Change Dir Action");

        if (Files.exists(Paths.get(workingDirPath.getPath().toString(), param)) && !param.equals("..") && Paths.get(workingDirPath.getPath().toString(), param).toFile().isDirectory()) {
            workingDirPath.setPath(Paths.get(workingDirPath.getPath().toString(), param));
            outputStream.writeUTF(workingDirPath.getPath().toString());
        } else if (param.equals("..") && workingDirPath.getPath().getParent() != null) {
            workingDirPath.setPath(Paths.get(workingDirPath.getPath().getParent().toString()));
            outputStream.writeUTF(workingDirPath.getPath().toString());
        } else {
            outputStream.writeUTF("Path does not exist or target is not a folder");
        }
    }
}
