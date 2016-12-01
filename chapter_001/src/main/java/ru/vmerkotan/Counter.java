package ru.vmerkotan;

/**
* The {@code Counter} class
* @author Vadim Merkotan
* @since  1.0
*/
public class Counter {

	/**
     * countes the amount of even numbers from
	 * range between start and end params
     *
     * @param   start   start of range
	 * @param   end   	end of range
	 * @return  amount of even numbers
     */
	public int add(int start, int end) {
		int result = 0;
		for (int i = start; i <= end; i++) {
			if(i %2 == 0) {
				result += i;
			}
		}
		return result;
	}

}