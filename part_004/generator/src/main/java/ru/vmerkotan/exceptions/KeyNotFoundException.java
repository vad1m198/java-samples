package ru.vmerkotan.exceptions;

/**
 * {@code KeyNotFoundException} class represents
 * an exception to be thrown when key was not found.
 *
 * Created by Вадим on 09.01.2017.
 */
public class KeyNotFoundException extends RuntimeException {
    /**
     * Creates new KeyNotFoundException object.
     *
     * @param message String message.
     */
    public KeyNotFoundException(String message) {
        super(message);
    }
}
