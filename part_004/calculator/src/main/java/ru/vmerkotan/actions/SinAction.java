package ru.vmerkotan.actions;

import ru.vmerkotan.exceptions.InvalidArgumentsException;

/**
 * {@code SinAction} class presents sinus
 * mathematical operation.
 *
 * Created by vmerkotan on 1/6/2017.
 */
public class SinAction implements Action {
    /**
     * Action key.
     */
    private String key;
    /**
     * Action description.
     */
    private String desc;

    /**
     * Constructs new SinAction object.
     * @param key   String key representation.
     * @param desc  String description.
     */
    public SinAction(String key, String desc) {
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
        if (params.length != 1) {
            throw new InvalidArgumentsException("Please enter only one argument");
        }
        double result;
        try {
            result = Math.sin(Double.parseDouble(params[0]));
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentsException("Argument is invalid. Should be a valid number");
        }
        return result;
    }
}
