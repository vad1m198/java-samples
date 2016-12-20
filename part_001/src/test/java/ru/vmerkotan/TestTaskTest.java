package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
* Tests for TestTask class.
* @author Vadim Merkotan
* @since  1.0
*/
public class TestTaskTest {

	/**
	* test contains method. Should return true
	* if origin string contains sub
	*/
	@Test
	public void whenOriginContainsSubThenReturnTrue() {
		TestTask task = new TestTask();
		assertEquals(true, task.contains("Hello123", "ello"));
	}

	/**
	* test contains method. Should return false
	* if origin string contains only part sub
	*/
	@Test
	public void whenOriginContainsPartSubThenReturnFalse() {
		TestTask task = new TestTask();
		assertEquals(false, task.contains("Hello123", "elloM"));
	}

	/**
	* test contains method. Should return false
	* if origin contains sub but with gaps return false
	*/
	@Test
	public void whenOriginContainsSubWithGapsThenReturnFalse() {
		TestTask task = new TestTask();
		assertEquals(false, task.contains("Hello123", "elo"));
	}

	/**
	* test contains method. Should return true
	* if origin string contains sub and starts with sub
	*/
	@Test
	public void whenOriginContainsAndStartsWithSubThenReturnTrue() {
		TestTask task = new TestTask();
		assertEquals(true, task.contains("Hello123", "Hell"));
	}

	/**
	* test contains method. Should return true
	* if origin string contains sub and ends with sub
	*/
	@Test
	public void whenOriginContainsAndEndsWithSubThenReturnTrue() {
		TestTask task = new TestTask();
		assertEquals(true, task.contains("Hello123", "123"));
	}

	/**
	* test contains method. Should return true
	* if origin string contains multi sub
	*/
	@Test
	public void whenOriginContainsMultiSubThenReturnTrue() {
		TestTask task = new TestTask();
		assertEquals(true, task.contains("123Hello123", "123"));
	}

	/**
	* test contains method. Should return true
	* if origin string contains multi entries
	*/
	@Test
	public void whenOriginContainsMultiEntriesThenReturnTrue() {
		TestTask task = new TestTask();
		assertEquals(true, task.contains("1123Hello", "123"));
	}
}