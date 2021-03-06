package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
* Tests for BubbleSort class.
* @author Vadim Merkotan
* @since  1.0
*/
public class BubbleSortTest {

	/**
	* test sort method. Should sort array descending
	*/
	@Test
	public void whenSortInvokedThenSortedArrayShouldBeReturned() {
		BubbleSort bs = new BubbleSort();
		int[] array = new int[]{82, -10, 11, 3, 15};
		assertThat(new int[]{82, 15, 11, 3, -10}, is(bs.sort(array)));
	}

	/**
	* test sort method. Should work properly with arrays with one
	* element
	*/
	@Test
	public void whenArrayLengthIsOneThenItShouldWorkAsWell() {
		BubbleSort bs = new BubbleSort();
		int[] array = new int[]{1};
		assertThat(new int[]{1}, is(bs.sort(array)));
	}
}