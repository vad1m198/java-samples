package ru.vmerkotan.actions;

import ru.vmerkotan.exceptions.InvalidArgumentsException;

/**
 * {@code DivideAction} class presents multiply
 * mathematical operation.
 *
 * Created by vmerkotan on 1/6/2017.
 */
public class MultiplyAction implements Action {
    /**
     * Action key.
     */
    private String key;
    /**
     * Action description.
     */
    private String desc;

    /**
     * Constructs new MultiplyAction object.
     * @param key   String key representation.
     * @param desc  String description.
     */
    public MultiplyAction(String key, String desc) {
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
        if (params.length == 0) {
            throw new InvalidArgumentsException("Please enter some arguments");
        }
        double result = 1;
        for (String s: params) {
            try {
                double d = Double.parseDouble(s);
                result *= d;
            } catch (NumberFormatException nfe) {
                throw new InvalidArgumentsException(s + " is invalid argument");
            }
        }
        return result;
    }
}
