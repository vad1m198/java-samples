package ru.vmerkotan;
/**
* The {@code Max} class предоставляет возможность.
* выполнения сравнения двух чисел
* @author Vadim Merkotan
* @since  1.0
*/
public class Max {

	/**
	* метод сравнивает 2 числа и возвращает большое.
	* @param first	первое число
	* @param second	второе число
	* @return	максимальное из двух переданых
	*/
	public int max(int first, int second) {
		return first > second ? first : second;
	}

	/**
	* метод сравнивает 3 числа и возвращает большое.
	* @param first	первое число
	* @param second	второе число
	* @param third	третье число
	* @return	максимальное из трех переданых
	*/
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}