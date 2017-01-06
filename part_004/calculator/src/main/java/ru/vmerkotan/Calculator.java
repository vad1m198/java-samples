package ru.vmerkotan;

import ru.vmerkotan.actions.Action;
import ru.vmerkotan.exceptions.InvalidKeyException;

import java.util.Arrays;

/**
* The {@code Calculator} class represents a container for
* actions to perform math operations.
 *
* @author Vadim Merkotan
*/
class Calculator {
    /**
     * Holds operation results.
     */
    private double result;
    /**
     * Inner actions array.
     */
    private Action[] actions = new Action[0];
    /**
     * current position in actions array.
     */
    private int position = 0;

    /**
     * Adds Action to inner actions array.
     *
     * @param action    Action instance to add.
     */
    void addAction(Action action) {
        if (action != null) {
            if (this.position == this.actions.length) {
                this.actions = Arrays.copyOf(this.actions, this.position == 0 ? 1 : this.actions.length * 2);
            }
        }
        this.actions[position++] = action;
    }

    /**
     * Executes Action from inner actions array found by passed key.
     * If some parameter equal -r it will be replaced with result value.
     *
     * @param key   String action key to execute.
     * @param params    String[] params to pass to Action.
     */
    void executeByKey(String key, String[] params) {
        boolean keyFound = false;
        for (Action ac: this.actions) {
            if (ac != null && ac.getKey().equals(key)) {
                for (int i = 0; i < params.length; i++) {
                    if ("-r".equals(params[i])) {
                        params[i] = String.valueOf(this.result);
                    }
                }
                this.result = ac.execute(params);
                keyFound = true;
                break;
            }
        }
        if (!keyFound) {
            throw new InvalidKeyException("Key: '" + key + "' was not recognized");
        }
    }

    /**
     * Returns result of operations.
     *
     * @return  value of result field.
     */
    double getResult() {
        return this.result;
    }

    /**
     * Gets description of all inner actions.
     *
     * @return  String description of all keys.
     */
    String getDescriptions() {
        StringBuilder sb = new StringBuilder();
        for (Action ac: this.actions) {
            if (ac != null) {
                sb.append(ac.getKey()).append("  -  ").append(ac.getDescription()).append(System.getProperty("line.separator"));
            }
        }
        return sb.toString();
    }
}
