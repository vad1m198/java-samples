package ru.vmerkotan;

public class ValidateInput extends ConsoleInput {
	
	public int ask(String question, int[] range) {
		boolean invalid = true;
		int value = -1;
		do {
			try {
				value = super.ask(question, range);
				invalid = false;
			} catch(NumberFormatException nfe){
				System.out.println("Please enter valid data again.");
			} catch(MenuOutException moe){
				System.out.println("Please select key from menu range.");
			}	
		} while(invalid);
		return value;
	}

}