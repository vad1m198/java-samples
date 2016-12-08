package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.*;
/**
* Tests for Factorial class
* @author Vadim Merkotan
* @since  1.0
*/
public class FactorialTest {
	
	/**
	* Test Factorial method. 
	* When base is 1 return 1;
	*/
	@Test
	public void whenBaseIsOneThenReturnOne() {
		Factorial factorial = new Factorial();
		assertEquals(1, factorial.factorial(1));
	}
	
	/**
	* Test Factorial method. 
	* When base is 3 return 6;
	*/
	@Test
	public void whenBaseIsThreeThenReturnSix() {
		Factorial factorial = new Factorial();
		assertEquals(6, factorial.factorial(3));
	}
	
	/**
	* Test Factorial method. 
	* When base is negative return 0;
	*/
	@Test
	public void whenBaseIsNegativeThenReturnZero() {
		Factorial factorial = new Factorial();
		assertEquals(0, factorial.factorial(-3));
	}
}