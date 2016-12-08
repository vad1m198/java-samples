package ru.vmerkotan;

/**
* The {@code Input} class represents an interaction
* with user
* @since  1.0
*/
public interface Input {
	
	/*
	* Asks  aquestion
	* @param question	Question to ask user
	* @return String	answer
	*/
	String ask(String question);
		
}