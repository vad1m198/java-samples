package ru.vmerkotan;
/**
* The {@code Calculator} class предоставляет возможность
* выполнения арифметических операций. Таких как сложение,
* вычитание, умножение, деление
* @author Vadim Merkotans
* @since  1.0
*/
public class Calculator {
	/**
	* переменная для хранения результатов вычислений.
	*/
	private double result;

	/**
	* метод складывает 2 числа и записывает результат.
	* в поле класса {@code result}
	* @param firstSummand	первое слагаемое
	* @param secondSummand	второе слагаемое
	*/
	public void add(double firstSummand, double secondSummand) {
		result = firstSummand + secondSummand;
	}

	/**
	* метод произодит вычитание 2 чисел и записывает результат.
	* в поле класса {@code result}
	* @param minuend	уменьшаемое
	* @param subtrahend	вычитаемое
	*/
	public void subtract(double minuend, double subtrahend) {
		result = minuend - subtrahend;
	}

	/**
	* метод произодит умножение 2 чисел и записывает результат.
	* в поле класса {@code result}
	* @param firstFactor	первый множитель
	* @param secondFactor	второй множитель
	*/
	public void multiply(double firstFactor, double secondFactor) {
		result = firstFactor * secondFactor;
	}

	/**
	* метод произодит деление и записывает результат.
	* в поле класса {@code result}
	* @param dividend	делимое
	* @param divider	делитель
	*/
	public void divide(double dividend, double divider) {
		result = dividend / divider;
	}

	/**
	* возвращает значение поля класса {@code result}.
	* @return result
	*/
	public double getResult() {
		return this.result;
	}
}