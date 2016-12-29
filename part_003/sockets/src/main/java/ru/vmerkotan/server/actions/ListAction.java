package ru.vmerkotan.server.actions;

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
     * Holds current working dir path.
     */
    private PathWrapper workingDirPath;
    /**
     * DataOutputStream to write results to.
     */
    private DataOutputStream outputStream;
    /**
     * Constructs new ListAction object.
     * @param key             String action key.
     * @param desc            Action description.
     * @param workingDirPath  Path to working folder.
     * @param outputStream    DataOutputStream to write results to.
     */
    public ListAction(String key, String desc, PathWrapper workingDirPath, DataOutputStream outputStream) {
        super(key, desc);
        this.workingDirPath = workingDirPath;
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
        StringBuilder sb = new StringBuilder();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(workingDirPath.getPath())) {
            for (Path entry: entries) {
                sb.append(entry.getFileName());
                sb.append(System.getProperty("line.separator"));
            }
        }
        outputStream.writeUTF(sb.toString());
        outputStream.flush();
    }
}
