package ru.vmerkotan;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
/**
* Tests for {@code Point} class
* @author Vadim Merkotan
* @since  1.0
*/
public class PointTest {
	
	/**
	* Test distanceTo.
	*/
	@Test
	public void whenDistanceIsOneThenDistanceToShouldReturnOne() {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(2, 1);
		
		assertThat(1.0, closeTo(p1.distanceTo(p2), 0.01));
	}
	
	/**
	* Test distanceTo. Verify that distance from first point to second
	* is equal to distance from second point to the first
	*/
	@Test
	public void testDistancesEquality() {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(2, 1);		
		assertThat(p1.distanceTo(p2), closeTo(p2.distanceTo(p1), 0.01));
	}
	
	/**
	* Test distanceTo. When 2 points are equal distanceTo should
	* return 0
	*/
	@Test
	public void whenPointsAreEqualDIStanceToShouldReturnZero() {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, 1);		
		assertThat(0.0, closeTo(p2.distanceTo(p1), 0.01));
	}
	
	/**
	* Test distanceTo. When coordinates are negative
	* distanceTo should correctly calculate distance
	* return 0
	*/
	@Test
	public void whenCoordinatesNegativeThenDistanceToShouldWork() {
		Point p1 = new Point(-1, 1);
		Point p2 = new Point(1, 1);		
		assertThat(2.0, closeTo(p2.distanceTo(p1), 0.01));
	}
	
}