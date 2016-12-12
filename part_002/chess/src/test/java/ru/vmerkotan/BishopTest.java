package ru.vmerkotan;

import ru.vmerkotan.figures.*;
import ru.vmerkotan.exceptions.*;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
* Tests for Bishop class
* 
* @author Vadim Merkotans
* @since  1.0
* @version $Id$
*/
public class BishopTest {
	
	/**
	* Test Bishop way method
	* x and y destination coordinates incremented
	*/
	@Test
	public void whenWayWithIncrementedCoorThenCellsArrayReturn() throws ImpossibleMoveException{
		Cell startCell = new Cell(4,3);
		Bishop b = new Bishop(startCell);
		Cell targetCell = new Cell(7,6);		
		Cell[] expectedResult = new Cell[3];
		expectedResult[0] = new Cell(5,4);
		expectedResult[1] = new Cell(6,5);
		expectedResult[2] = new Cell(7,6);
		assertThat(b.way(targetCell), is(expectedResult));		
	}
	
	/**
	* Test Bishop way method
	* x and y destination coordinates decremented
	*/
	@Test
	public void whenWayWithDecrementedCoorThenCellsArrayReturn() throws ImpossibleMoveException{
		Cell startCell = new Cell(7,6);
		Bishop b = new Bishop(startCell);
		Cell targetCell = new Cell(4,3);		
		Cell[] expectedResult = new Cell[3];
		expectedResult[0] = new Cell(6,5);
		expectedResult[1] = new Cell(5,4);		
		expectedResult[2] = new Cell(4,3);
		assertThat(b.way(targetCell), is(expectedResult));		
	}
	
	/**
	* Test Bishop way method
	* If bishop could not be moved to target Cell
	* ImpossibleMoveException expected to be thrown
	*/
	@Test(expected = ImpossibleMoveException.class)
    public void testDivisionWithException()  throws ImpossibleMoveException{
        Cell startCell = new Cell(4,3);
		Bishop b = new Bishop(startCell);
		Cell targetCell = new Cell(1,1);		
		b.way(targetCell);		
    }
}
