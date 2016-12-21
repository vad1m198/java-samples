package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
* The {@code BracketsTest} class represents tests
* for Brackets class.
* @author Vadim Merkotan
* @since  1.0
*/
public class BracketsTest {

	/**
	* tests verifyParenthesesOrder method.
	*/
	@Test
	public void whenPassOneSignThenFalse() {
		Brackets br = new Brackets();
		assertFalse(br.verifyParenthesesOrder("("));
	}

	/**
	* tests verifyParenthesesOrder method.
	*/
	@Test
	public void whenPassComplicatedCorrectOrderThenTrue() {
		Brackets br = new Brackets();
		assertTrue(br.verifyParenthesesOrder("((1+2)*(3+4)*(4+5)/((9+5)/(3+2)))"));
	}

	/**
	* tests verifyParenthesesOrder method.
	*/
	@Test
	public void whenPassComplicatedInCorrectOrderThenFalse() {
		Brackets br = new Brackets();
		assertFalse(br.verifyParenthesesOrder("(())()(()()))"));
	}
}