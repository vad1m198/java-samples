package ru.vmerkotan.manager;

import ru.vmerkotan.Action;

import java.io.IOException;
import java.util.Arrays;

/**
 * ActionsManager class represents container for
 * methods to hold Actions and work with them.
 * Created by vmerkotan on 12/29/2016.
 */
public class ActionsManager {
    /**
     * holds all actions from manager.
     */
    private Action[] actions = new Action[1];
    /**
     * Current position.
     */
    private int position = 0;

    /**
     * Adds action to internal array.
     * @param a Action to add.
     */
    public void addAction(Action a) {
        if (position == actions.length) {
            Action[] newActions = new Action[actions.length * 2];
            System.arraycopy(actions, 0, newActions, 0, actions.length);
            actions = newActions;
        }
        actions[position++] = a;
    }

    /**
     * Returns all actions from manager.
     * @return Action[] actions
     */
    public Action[] getAllActions() {
        return Arrays.copyOfRange(actions, 0, position);
    }

    /**
     * Selects action by key and executes it.
     * @param str   String to parse
     * @throws IOException when exception appear
     */
    public void executeByKey(String str) throws IOException {
        String key = str.split(" ")[0];
        String params = str.split(" ").length > 1 ? str.split(" ")[1] : "";
        boolean wasFound = false;
        for (Action a: this.getAllActions()) {
            if (a.getKey().equals(key)) {
                a.execute(params);
                wasFound = true;
                break;
            }
        }
        if (!wasFound) {
            throw new InvalidActionKeyException("'" + key + "'" + " command was not recognized");
        }
    }
}
