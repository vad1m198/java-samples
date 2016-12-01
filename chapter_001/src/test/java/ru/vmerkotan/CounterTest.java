package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.*;
/**
* Tests for Counter class
* @author Vadim Merkotan
* @since  1.0
*/
public class CounterTest {

	/**
    * Test add method. When range starts and ends 
	* with even number then it should be added to result
    */
	@Test
	public void whenEvenOnTheEdgeThenShouldBeAddedToResult() {
		Counter counter = new Counter();
		assertEquals(12, counter.add(2,6) );
	}
	
	/**
    * Test add method. When range starts and ends 
	* on the same even number then result should be equal 
	* this number
    */
	@Test
	public void whenRangeStartsAndEndsWithTheSameEvenThenReturnThisEven() {
		Counter counter = new Counter();
		assertEquals(2, counter.add(2,2) );
	}
	
	/**
    * Test add method. When range starts and ends 
	* on the same odd number then result should be 0
    */
	@Test
	public void whenRangeStartsAndEndsWithTheSameOddThenReturnZero() {
		Counter counter = new Counter();
		assertEquals(0, counter.add(1,1) );
	}
	
	/**
    * Test add method. When range starts and ends 
	* with negative numbers result should be relevant
    */
	@Test
	public void whenRangeNegativeThenReturnRelevantAmount() {
		Counter counter = new Counter();
		assertEquals(-10, counter.add(-6,-3) );
	}

}