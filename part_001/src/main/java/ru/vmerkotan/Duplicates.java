package ru.vmerkotan;

import java.util.Arrays;

/**
* The {@code Duplicates} class.
* @author Vadim Merkotan
* @since  1.0
*/
class Duplicates {

	/**
	* Remove duplicates from String[].
	* @param  arr	String[] to remove duplicates
	* @return	String[] with unique values
	*/
    String[] removeDuplicates(String[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] != null && arr[i].equals(arr[j])) {
                    arr[j] = null;
                    counter++;
                }
            }
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] == null && arr[j + 1] != null) {
                    arr[j] = arr[j + 1];
                    arr[j + 1] = null;
                }
            }
        }
        return Arrays.copyOf(arr, arr.length - counter);
    }
}