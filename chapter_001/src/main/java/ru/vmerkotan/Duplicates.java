package ru.vmerkotan;

import java.util.Arrays;

/**
* The {@code Duplicates} class
* @author Vadim Merkotan
* @since  1.0
*/
public class Duplicates {
	
	/**
	* Remove duplicates from String[]
	* @param  String[]	to remove duplicates
	* @return	String[] with unique values
	*/
	public String[] removeDuplicates(String[] arr) {		
		for (int i = 0; i < arr.length; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[i].equals(arr[j]) ) {
					arr[j] = arr[arr.length - 1];
					arr = Arrays.copyOf(arr, arr.length - 1);
				}
			}			
		}
		
		
		return arr;
	}
	
}