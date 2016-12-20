package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
* Tests for MergeSortedArray class.
* @author Vadim Merkotan
* @since  1.0
*/
public class MergeSortedArrayTest {

	/**
	* test merge method.
	* params should be ascending sorted
	*/
	@Test
	public void whenPassSortedArraysThenMergedSortedArrayShouldBeReturned() {
		MergeSortedArray msa = new MergeSortedArray();
		int[] first = new int[]{1, 3, 5, 7};
		int[] second = new int[]{2, 4, 6, 8};
		assertThat(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, is(msa.merge(first, second)));
	}

	/**
	* test merge method.
	* params should be ascending sorted
	* If pass sorted arrays with disjoint numbers
	* method should work
	*/
	@Test
	public void whenDisjointArraysThenMergedAppropriate() {
		MergeSortedArray msa = new MergeSortedArray();
		int[] first = new int[]{1, 3, 5, 7};
		int[] second = new int[]{9, 10, 11, 15};
		assertThat(new int[]{1, 3, 5, 7, 9, 10, 11, 15}, is(msa.merge(first, second)));
	}

	/**
	* test merge method.
	* params should be ascending sorted
	* Test with negative numbers
	*/
	@Test
	public void whenPassNegativeThenMergedAppropriate() {
		MergeSortedArray msa = new MergeSortedArray();
		int[] first = new int[]{-3, -1, 5, 7};
		int[] second = new int[]{-2, 0, 4, 15};
		assertThat(new int[]{-3, -2, -1, 0, 4, 5, 7, 15}, is(msa.merge(first, second)));
	}
}