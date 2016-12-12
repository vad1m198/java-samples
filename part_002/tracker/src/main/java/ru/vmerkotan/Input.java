package ru.vmerkotan;

/**
* The {@code Input} class represents an interaction
* with user
* @since  1.0
*/
public interface Input {
	
	/*
	* Asks  a question
	* @param question	Question to ask user
	* @return String	answer
	*/
	String ask(String question);
	
	int ask(String question, int[] range);
	
	long ask(String question, long[] range);
}