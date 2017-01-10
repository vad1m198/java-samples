package ru.vmerkotan.exceptions;

/**
 * {@code InvalidArgumentsException} class represents
 * exception to be thrown when passed arguments are invalid.
 *
 * Created by vmerkotan on 1/6/2017.
 */
public class InvalidArgumentsException extends RuntimeException {
    /**
     * Constructs new InvalidArgumentsException object.
     *
     * @param message   reason of exception.
     */
    public InvalidArgumentsException(String message) {
        super(message);
    }
}
