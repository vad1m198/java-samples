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
	* test turn method.
	*/
	//@Test
	public void test() {
		SquareArray sa = new SquareArray();
		int[][] testArray = new int[][]{
		  { 1, 1 },
		  { 0, 0 }
		};
		int[][] expectedArray = new int[][]{
		  { 0, 1 },
		  { 0, 1 }
		};		
		assertThat(expectedArray, is(sa.turn(testArray)));
	}
}