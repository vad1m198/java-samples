package ru.vmerkotan;

/**
* The {@code Factorial} class.
* @author Vadim Merkotan
* @since  1.0
*/
public class Factorial {

	/**
	* calculates factorial for {@code base} parameter.
	* For all {@code base} parameters below 1 return 0
	*
	* @param base	int for which factorial should be calculated
	* @return		factorial for given {@code base}
	*/
	public int factorial(int base) {
		if (base < 1) {
			return 0;
		}
		return  base == 1 ? 1 : base * factorial(base - 1);
	}
}