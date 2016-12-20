package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
* Tests for {@code Max} class.
* @author Vadim Merkotan
* @since  1.0
*/
public class MaxTest {

	/**
	* Test max method with 2 params. If first param is greater then second
	* then first param should be returned
	*/
	@Test
	public void whenFistIsGreaterSecondThenReturnFirst() {
		Max max = new Max();
		int result = max.max(2, 1);
		assertEquals(2, result);
	}

	/**
	* Test max method with 2 params. If second param is greater then second
	* then second param should be returned
	*/
	@Test
	public void whenSecondIsGreaterFirstThenReturnFirst() {
		Max max = new Max();
		int result = max.max(1, 3);
		assertEquals(3, result);
	}

	/**
	* Test max method with 2 params. If params are equal then
	* return one of params
	*/
	@Test
	public void whenParamsAreEqualThenReturnAnyone() {
		Max max = new Max();
		int result = max.max(2, 2);
		assertEquals(2, result);
	}

	/**
	* Test max method with 3 params. Should return the
	* greatest number
	*/
	@Test
	public void whenFistIsTeeGreatestThenReturnFirst() {
		Max max = new Max();
		int result = max.max(5, 4, 3);
		assertEquals(5, result);
	}

	/**
	* Test max method with 3 params. If params are equal then
	* return one of params
	*/
	@Test
	public void whenParamsAreTheSameThenReturnAnyone() {
		Max max = new Max();
		int result = max.max(4, 4, 4);
		assertEquals(4, result);
	}
}