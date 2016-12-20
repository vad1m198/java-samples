package ru.vmerkotan;

/**
* The {@code Triangle} class представляет
* треугольник на плоскости.
* @author Vadim Merkotan
* @since  1.0
*/
public class Triangle {
	/**
	* поле для хранения {@code Point} треугольника.
	*/
	private Point a;
	/**
	 * поле для хранения {@code Point} треугольника.
	 */
	private Point b;
	/**
	 * поле для хранения {@code Point} треугольника.
	 */
	private Point c;

	/**
     * Создает новый обьет {@code Triangle}.
     *
     * @param   a   первая {@code Point} треугольника
	 * @param   b   вторая {@code Point} треугольника
	 * @param   c   третья {@code Point} треугольника
     */
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
     * Считает площадь треугольника.
     *
	 * @return  площадь треугольника
     */
	public double area() {
		double abDistance = this.a.distanceTo(b);
		double bcDistance = this.b.distanceTo(c);
		double acDistance = this.a.distanceTo(c);
		double perimeter = (abDistance + bcDistance + acDistance) / 2;
		return Math.sqrt(perimeter * (perimeter - abDistance) * (perimeter - bcDistance) * (perimeter - acDistance));
	}
}