package ru.vmerkotan;

/**
* The {@code MergeSortedArray} class.
* @author Vadim Merkotan
* @since  1.0
*/
public class MergeSortedArray {

	/**
	* merges given sorted arrays in one
	* work only with ascending sorted arrays.
	*
	* @param first	first sorted array
	* @param second	second sorted array
	* @return		merged sorted ascending array
	*/
	public int[] merge(int[] first, int[] second) {
		int[] result = new int[first.length + second.length];
		int firstArrPointer = 0;
		int secondArrPointer = 0;
		for (int i = 0; i < result.length; i++) {
			if (firstArrPointer == first.length) {
				result[i] = second[secondArrPointer++];
			} else if (secondArrPointer == second.length) {
				result[i] = first[firstArrPointer++];
			} else {
result[i] = first[firstArrPointer] > second[secondArrPointer]
					? second[secondArrPointer++] : first[firstArrPointer++];
			}
		}
		return result;
	}
}