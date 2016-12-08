package ru.vmerkotan;

import java.util.*;

/**
* The {@code ConsoleInput} class represents an interaction
* with user through console
* @since  1.0
*/
public class ConsoleInput implements Input {
	
	private Scanner scanner = new Scanner(System.in);
	
	/*
	* Asks  aquestion
	* @param question	Question to ask user
	* @return String	answer readed from console
	*/
	public String ask(String question) {		
		System.out.println(question);
		return this.scanner.nextLine();
	}
	
}