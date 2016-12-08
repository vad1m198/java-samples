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
	//@Test
	public void verifyTrackerRunnerMainMethod() {
		Input input = new StubInput(
			new String[] {"2", "new name", "new description", "1", "6", "name", "7", "1481125321498", "0"}
		);
		new TrackerRunner(input).init();		
	}
}