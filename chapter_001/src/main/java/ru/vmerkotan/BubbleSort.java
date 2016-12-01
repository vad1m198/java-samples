package ru.vmerkotan;

/**
* The {@code BubbleSort} class
* @author Vadim Merkotan
* @since  1.0
*/
public class BubbleSort {
	
	/**
	* sorts given array with bubble algorithm descending
	*
	* @param array	array which should be sorted
	* @return		sorted array
	*/
	public int[] sort(int[] array) {
		if(array.length < 2) {
			return array;
		}		
		for(int i = 1; i < array.length; i++) {
			int current = array[i];
			int previous = array[i-1];
			if(current < previous) {
				array[i-1] = current;
				array[i] = previous;
			}
		}
		return array;
	}
}