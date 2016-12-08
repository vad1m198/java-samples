package ru.vmerkotan;

/**
* The {@code Turn} class
* @author Vadim Merkotan
* @since  1.0
*/
public class Turn {
	
	/**
	* turns the array
	*
	* @param array	array needed to be turned
	* @return       turned array
	*/
	public int[] back(int[] array) {
		if(array.length < 2) {
			return array;
		}
		for(int i = 0; i < array.length / 2; i++) {
			int first = array[i];
			int last = array[array.length - 1 - i];
			array[i] = last;
			array[array.length - 1 - i] = first;
		}
		return array;
	}
}