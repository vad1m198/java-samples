package ru.vmerkotan.actions;

/**
 * {@code Action} class represents abstract
 * mathematical action.
 *
 * Created by vmerkotan on 1/6/2017.
 */
public interface Action {

    /**
     * Returns Action description.
     *
     * @return description
     */
    String getDescription();

    /**
     * Returns Action key representation.
     *
     * @return key.
     */
    String getKey();

    /**
     * Executes action.
     *
     * @param params    String[] params to work with.
     * @return  result of action.
     */
    double execute(String[] params);
}
