package ru.vmerkotan;
/**
* The {@code Max} class предоставляет возможность
* выполнения сравнения двух чисел
* @author Vadim Merkotans
* @since  1.0
*/
public class Max {

	/**
	* метод сравнивает 2 числа и возвращает большое
	* @param first	первое число
	* @param second	второе число
	*/
	public int max(int first, int second) {
		return first > second ? first : second;
	}
}