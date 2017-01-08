package ru.vmerkotan.exceptions;

/**
 * {@code StoreIsFullException} class represents
 * an exception to be thrown when store is full.
 *
 * Created by Вадим on 08.01.2017.
 */
public class StoreIsFullException extends RuntimeException {
    /**
     * Creates new StoreIsFullException object.
     *
     * @param message String message.
     */
    public StoreIsFullException(String message) {
        super(message);
    }
}
