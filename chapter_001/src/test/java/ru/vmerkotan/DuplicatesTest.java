package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
* Tests for Duplicates class
* @author Vadim Merkotan
* @since  1.0
*/
public class DuplicatesTest {
	
	/**
	* test removeDuplicates method.
	* should return without duplicates
	*/
	@Test
	public void whenPassArrayWithDuplicatesThenShouldReturnWithoutDuplicates() {
		Duplicates duplicates = new Duplicates();
		String[] originArray = new String[]{"Hi", "Hello", "Hello", "Hi"};
		String[] expectedArray = new String[]{"Hi", "Hello"};
		assertThat(expectedArray, is(duplicates.removeDuplicates(originArray)) );
	}
}