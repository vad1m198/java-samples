package ru.vmerkotan;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
/**
* Tests for {@code Point} class
* @author Vadim Merkotan
* @since  1.0
*/
public class TriangleTest {
	
	/**
	* Test area method.
	*/
	@Test
	public void whenAreaIsTwoThenAreaMethodShouldReturnTwo() {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(3, 1);
		Point p3 = new Point(3, 3);
		Triangle t = new Triangle(p1, p2, p3);
		
		assertThat(2.0, closeTo(t.area(), 0.01));
	}
	
	/**
	* Test area method. When 2 points are equal then
	* area should be 0
	*/
	@Test
	public void whenTwoPointEqualThenAreaShouldBeZero() {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(3, 3);
		Triangle t = new Triangle(p1, p2, p3);
		
		assertThat(0.0, closeTo(t.area(), 0.01));
	}
}