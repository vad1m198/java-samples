package ru.vmerkotan.manager;

/**
 * Represent Exception to be thrown when key in not
 * recognized.
 * Created by vmerkotan on 12/29/2016.
 */
public class InvalidActionKeyException extends RuntimeException {
    /**
     * Initiates new InvalidActionKeyException instance.
     * @param s message
     */
    InvalidActionKeyException(String s) {
        super(s);
    }
}
