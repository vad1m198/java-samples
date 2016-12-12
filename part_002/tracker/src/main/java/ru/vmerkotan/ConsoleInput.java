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
		
	public int ask(String question, int[] range) {
		int key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for(int value: range) {
			if(value == key) {
				exist = true;
				break;
			}
		}
		if(exist) {
			return key;
		} else {
			throw new MenuOutException("Out of menu range.");
		}
	}
	
	public long ask(String question, long[] range) {
		long key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for(long value: range) {
			if(value == key) {
				exist = true;
				break;
			}
		}
		if(exist) {
			return key;
		} else {
			throw new InvalidIdintifierException("Not in idintifiers range.");
		}
	}
	
}