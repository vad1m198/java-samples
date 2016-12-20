package ru.vmerkotan;

import java.util.Arrays;
/**
* The {@code TestTask} class represent
* this final task for chapter001.
* @author Vadim Merkotan
* @since  1.0
*/
public class TestTask {

	/**
	* Verifies that origin string contains
	* sub String.
	* @param origin	String to check with
	* @param sub	String to check what
	* @return		true if origin contains sub else return false
	*/
	public boolean contains(String origin, String sub) {
		char[] originArray = origin.toCharArray();
		char[] subArray = sub.toCharArray();
		if (subArray.length > originArray.length) {
			return false;
		}

		for (int i = 0; i < originArray.length; i++) {
			if (originArray[i] == subArray[0]) {
				boolean result = true;
				char[] originSubArray = Arrays.copyOfRange(originArray, i, i + subArray.length);
				for (int j = 0; j < subArray.length; j++) {
					if (subArray[j] != originSubArray[j]) {
						result = false;
					}
				}
				if (result) {
					return true;
				}
			}
		}
		return false;
	}
}