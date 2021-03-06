package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
* Tests for Paint class.
* @author Vadim Merkotan
* @since  1.0
*/
public class PaintTest {

	/**
	* test for piramid method. When Heigth is 0
	* then return empty string
	*/
	@Test
	public void whenHeightIsZeroThenReturnEmptyString() {
		Paint paint = new Paint();
		assertEquals("", paint.piramid(0));
	}

	/**
	* test for piramid method. When Heigth is 1
	* then return '^'
	*/
	@Test
	public void whenHeightIsOneThenReturnOneSignString() {
		Paint paint = new Paint();
		assertEquals("^", paint.piramid(1));
	}

	/**
	* test for piramid method. When Heigth is greater then 1
	* then return piramid picture
	*/
	@Test
	public void whenHeightIsGreaterOneThenReturnPiramid() {
		Paint paint = new Paint();
		assertEquals(" ^ " + System.getProperty("line.separator") + "^ ^ " + System.getProperty("line.separator"), paint.piramid(2));
	}
}