package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.*;

/**
* Tests for {@code Max} class
* @author Vadim Merkotan
* @since  1.0
*/
public class MaxTest {

	/**
	* Test max method. If first param is greater then second
	* then first param should be returned
	*/
	@Test
	public void whenFistIsGreaterSecondThenReturnFirst() {
		Max max = new Max();
		int result = max.max(2,1);
		assertEquals(2, result);
	}
	
	/**
	* Test max method. If second param is greater then second
	* then second param should be returned
	*/
	@Test
	public void whenSecondIsGreaterFirstThenReturnFirst() {
		Max max = new Max();
		int result = max.max(1,3);
		assertEquals(3, result);
	}
	
	/**
	* Test max method. If params are equals then
	* the return one of params
	*/
	@Test
	public void whenParamsAreEqualThenReturnAnyone() {
		Max max = new Max();
		int result = max.max(2,2);
		assertEquals(2, result);
	}
}