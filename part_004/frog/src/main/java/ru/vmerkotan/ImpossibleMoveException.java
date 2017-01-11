package ru.vmerkotan;

/**
 * {@code ImpossibleMoveException} class represents
 * an exception to be thrown if move is impossible.
 *
 * Created by vmerkotan on 1/11/2017.
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Creates new ImpossibleMoveException object.
     *
     * @param message String message to explain the exception.
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
