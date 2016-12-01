package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
/**
* Tests for Turn class
* @author Vadim Merkotan
* @since  1.0
*/
public class TurnTest {
	
	/**
	* test for back method. When array contain 1 element
	* then array should not be changed
	*/
	@Test
	public void whenArrayLengthIsOneThenReturnOriginArray() {
		Turn turn = new Turn();
		int[] array = new int[]{1};
		assertEquals(array, turn.back(array));
	}
	
	/**
	* test for back method. When array contain more than 1 element
	* then turned array should be returned
	*/
	@Test
	public void whenArrayLengthIsGreatedOneThenReturnTurnedArray() {
		Turn turn = new Turn();
		int[] array = new int[]{1,2,3};
		assertThat(new int[]{3,2,1}, is(turn.back(array)));
	}
}