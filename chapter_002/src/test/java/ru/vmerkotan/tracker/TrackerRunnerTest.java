package ru.vmerkotan.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
* Tests for TrackerRunner class
* @author Vadim Merkotan
* @since  1.0
*/
public class TrackerRunnerTest {
	
	/**
	* Run main method
	*/
	@Test
	public void whenAddTicketsThenEqualArrayReturned() {
		TrackerRunner runner = new TrackerRunner();
		runner.main(new String[0]);
	}
}