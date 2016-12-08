package ru.vmerkotan;

/**
* The {@code SquareArray} class
* @author Vadim Merkotan
* @since  1.0
*/
public class SquareArray {
	
	/**
	* rotates array by 90 degrees clockwise.
	* Works only with square arrays
	*/
	public int[][] rotate(int[][] arr) {
		int temp;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length - 1; j++) {
				temp = arr[i][j + 1];
				arr[i][j + 1] = arr[j + 1][i];
				arr[j + 1][i] = temp;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length / 2; j++) {
				temp = arr[i][j];
				arr[i][j] = arr[i][arr.length - j - 1];
				arr[i][arr.length - j - 1] = temp;
			}
		}
		return arr;
	}
	
}