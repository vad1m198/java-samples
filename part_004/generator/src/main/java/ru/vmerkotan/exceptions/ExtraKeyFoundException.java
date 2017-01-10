package ru.vmerkotan.exceptions;

/**
 * {@code ExtraKeyFoundException} class represents
 * an exception to be thrown when key is present in dictionary but was not found in template.
 *
 * Created by Вадим on 09.01.2017.
 */
public class ExtraKeyFoundException extends RuntimeException {
    /**
     * Creates new ExtraKeyFoundException object.
     *
     * @param message String message.
     */
    public ExtraKeyFoundException(String message) {
        super(message);
    }
}