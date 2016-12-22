package ru.vmerkotan;

/**
 * Represents Exception when select is out of mewnu range.
 */
public class MenuOutException extends RuntimeException {
    /**
     * Constructs new object.
     * @param msg message to add to exception
     */
	public MenuOutException(String msg) {
		super(msg);
	}
}