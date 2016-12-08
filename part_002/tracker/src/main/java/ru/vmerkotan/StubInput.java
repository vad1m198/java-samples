package ru.vmerkotan;

import java.util.*;
/**
* The {@code StubInput} class represents Input interface for Tests
* @since  1.0
*/
public class StubInput implements Input {
	private String[] answers;
	private int position = 0;
	
	public StubInput(String[] answers) {
		this.answers = answers;
	}
	
	public String ask(String question) {		
		return answers[position++];
	}
	
	public int ask(String question, int[] range) {		
		return Integer.valueOf(this.ask(question));
		
	}
	
}