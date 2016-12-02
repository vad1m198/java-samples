package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
* Tests for SquareArray class
* @author Vadim Merkotan
* @since  1.0
*/
public class SquareArrayTest {
	
	/**
	* test rotate method.
	*/
	@Test
	public void shouldRotateSquareArray() {
		SquareArray sa = new SquareArray();
		int[][] testArray = new int[][]{
		  { 1, 2, 3 },
		  { 1, 2, 3 },
		  { 1, 2, 3 },
		};
		int[][] expectedArray = new int[][]{
		  { 1, 1, 1 },
		  { 2, 2, 2 },
		  { 3, 3, 3 },
		};		
		assertThat(expectedArray, is(sa.rotate(testArray)));
	}
}