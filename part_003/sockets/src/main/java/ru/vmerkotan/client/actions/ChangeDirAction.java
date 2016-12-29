package ru.vmerkotan.client.actions;

import ru.vmerkotan.Action;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Action to change working folder.
 * Created by vmerkotan on 12/29/2016.
 */
public class ChangeDirAction extends Action {

    /**
     * DataOutputStream to write results to.
     */
    private DataOutputStream outputStream;
    /**
     * Constructs new ChangeDirAction object.
     *
     * @param key             String action key.
     * @param desc            Action description.
     * @param outputStream    DataOutputStream to write results to.
     */
    public ChangeDirAction(String key, String desc, DataOutputStream outputStream) {
        super(key, desc);
        this.outputStream = outputStream;
    }

    /**
     * Changes working dir.
     *
     * @throws IOException when exception appear.
     */
    @Override
    public void execute(String param) throws IOException {
        outputStream.writeUTF(getKey() + " " + param);
    }
}