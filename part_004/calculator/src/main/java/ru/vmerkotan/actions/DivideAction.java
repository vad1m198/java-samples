package ru.vmerkotan.actions;

import ru.vmerkotan.exceptions.InvalidArgumentsException;

/**
 * {@code DivideAction} class presents divide
 * mathematical operation.
 *
 * Created by vmerkotan on 1/6/2017.
 */
public class DivideAction implements Action {
    /**
     * Action key.
     */
    private String key;
    /**
     * Action description.
     */
    private String desc;

    /**
     * Constructs new DivideAction object.
     * @param key   String key representation.
     * @param desc  String description.
     */
    public DivideAction(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public double execute(String[] params) {
        if (params.length != 2) {
            throw new InvalidArgumentsException("Please enter two arguments");
        }
        double result;
        try {
            result = Double.parseDouble(params[0]) / Double.parseDouble(params[1]);
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentsException("Arguments are invalid. Should be a valid numbers");
        }
        return result;
    }
}