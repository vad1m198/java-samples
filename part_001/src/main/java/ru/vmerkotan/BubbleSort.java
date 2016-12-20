package ru.vmerkotan;

/**
* The {@code BubbleSort} class.
* @author Vadim Merkotan
* @since  1.0
*/
public class BubbleSort {

	/**
	* sorts given array with bubble algorithm descending.
	*
	* @param array	array which should be sorted
	* @return		sorted array
	*/
	public int[] sort(int[] array) {
		if (array.length < 2) {
			return array;
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				int current = array[j];
				int next = array[j + 1];
				if (current < next) {
					array[j + 1] = current;
					array[j] = next;
				}
			}
		}
		return array;
	}
}