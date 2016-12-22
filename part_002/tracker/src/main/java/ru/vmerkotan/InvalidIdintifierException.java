package ru.vmerkotan;

/**
 * Represents Exception when id is invalid.
 */
public class InvalidIdintifierException extends RuntimeException {
    /**
     * Constructs new object.
     * @param msg message to add
     */
	public InvalidIdintifierException(String msg) {
		super(msg);
	}
}