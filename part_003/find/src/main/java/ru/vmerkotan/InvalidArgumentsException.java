package ru.vmerkotan;

/**
 * Represents Exception when id is invalid.
 */
public class InvalidArgumentsException extends RuntimeException {
    /**
     * Constructs new object.
     * @param msg message to add
     */
	public InvalidArgumentsException(String msg) {
		super(msg);
	}
}