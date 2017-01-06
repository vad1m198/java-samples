package ru.vmerkotan.exceptions;

/**
 * {@code InvalidKeyException} class represents
 * exception to be thrown when passed key is invalid.
 *
 * Created by vmerkotan on 1/6/2017.
 */
public class InvalidKeyException extends RuntimeException {
    /**
     * Constructs new InvalidKeyException object.
     *
     * @param message   reason of exception.
     */
    public InvalidKeyException(String message) {
        super(message);
    }
}
