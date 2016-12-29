package ru.vmerkotan;

import java.io.IOException;

/**
 * Action class represents abstract action in application.
 * Created by vmerkotan on 12/29/2016.
 */
public abstract class Action {
    /**
     * Action key.
     */
    private String key;
    /**
     * key description.
     */
    private String desc;

    /**
     * Initiates new Action.
     * @param key   String action key.
     * @param desc  Action description.
     */
    public Action(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * performs Action.
     * @param param Parameters to run action with.
     * @throws IOException when exception appear.
     */
    public abstract void execute(String param) throws IOException;

    /**
     * Returns Action key.
     * @return  key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Returns action description.
     * @return desc
     */
    public String getDescription() {
        return this.desc;
    }
}
