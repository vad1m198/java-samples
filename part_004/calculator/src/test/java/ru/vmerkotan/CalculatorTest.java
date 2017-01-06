package ru.vmerkotan;

//import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
* Tests for Calculator class.
*
* @author Vadim Merkotans
* @since  1.0
* @version $Id$
*/
public class CalculatorTest {

	/**
	* Test add method. When add 1 and 2 result should
	* be 3 and should be written to result field
	*/
//	@Test
	public void whenAddOneAndTwoResultShouldBeThree() {
		Calculator calc = new Calculator();
//		calc.add(1, 2);
		assertEquals(3.0, calc.getResult(), 0.0);
	}

	/**
	* Test subtract method. When subtract 1 from 5 result should
	* be 4 and should be written to result field
	*/
//	@Test
	public void whenSubtractOneFromFiveResultShouldBeFour() {
		Calculator calc = new Calculator();
//		calc.subtract(5, 1);
		assertEquals(4.0, calc.getResult(), 0.0);
	}

	/**
	* Test multiply method. When multiply 2 and 3 result should
	* be 6 and should be written to result field
	*/
//	@Test
	public void whenMultiplyTwoAndThreeResultShouldBeSix() {
		Calculator calc = new Calculator();
//		calc.multiply(2, 3);
		assertEquals(6.0, calc.getResult(), 0.0);
	}

	/**
	* Test divide method. When divide 8 by 2 result should
	* be 4 and should be written to result field
	*/
//	@Test
	public void whenDivideEightByTwoResultShouldBeFour() {
		Calculator calc = new Calculator();
//		calc.divide(8, 2);
		assertEquals(4.0, calc.getResult(), 0.0);
	}
}