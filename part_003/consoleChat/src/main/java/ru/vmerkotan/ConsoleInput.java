package ru.vmerkotan;

import java.util.Scanner;

/**
* The {@code ConsoleInput} class represents an interaction
* with user through console.
* @since  1.0
*/
public class ConsoleInput implements Input {
	/**
	 * Inner scanner to read from console.
	 */
	private Scanner scanner = new Scanner(System.in);

	/**
	* Returns string typed into console.
	* @return String	readed from console
	*/
	public String read() {
		return this.scanner.nextLine();
	}
}