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
		String[] originArray = new String[]{"w10","w20","w10","w30","w20","w40","w50","w50"};
		String[] expectedArray = new String[]{"w10","w20","w30","w40","w50"};
		assertThat(duplicates.removeDuplicates(originArray), is(expectedArray) );
	}

    /**
     * test removeDuplicates method.
     * should return array without changes if no duplicates is present
     */
    @Test
    public void whenPassArrayWithoutDuplicatesThenShouldReturnUnchagedArray() {
        Duplicates duplicates = new Duplicates();
        String[] originArray = new String[]{"Hi", "Hello"};
        String[] expectedArray = new String[]{"Hi", "Hello"};
        assertThat(duplicates.removeDuplicates(originArray), is(expectedArray) );
    }

    /**
     * test removeDuplicates method.
     * should return array length of 1
     * if origin array contains just duplicates
     */
    @Test
    public void whenPassArrayWithSameThenShouldReturnArrayOneLength() {
        Duplicates duplicates = new Duplicates();
        String[] originArray = new String[]{"Hi", "Hi", "Hi", "Hi"};
        String[] expectedArray = new String[]{"Hi"};
        assertThat(duplicates.removeDuplicates(originArray), is(expectedArray) );
    }

}