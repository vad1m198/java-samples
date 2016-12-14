package ru.vmerkotan;

/**
* The {@code Brackets} class represents container
* for methods to verify String with brackets chars order 
* @author Vadim Merkotan
* @since  1.0
*/
public class Brackets {
	/**
	* verifies that parenteses have a correct order in parStr
	* @param	parStr String to verify
	* @return 	true if parenteses order is correct in passed param
	*			else false
	*/
	public boolean verifyParenthesesOrder(String parStr) {
		int parenthesesCounter = 0;
		for(int i = 0; i < parStr.length(); i++) {
			if(parStr.charAt(i) == '(') {
				parenthesesCounter++;
			} else if(parStr.charAt(i) == ')') {
				parenthesesCounter--;
			}
			if(parenthesesCounter < 0) {
				return false;
			}
		}
		return parenthesesCounter == 0;
	}
	
}