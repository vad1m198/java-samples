package ru.vmerkotan;

import ru.vmerkotan.figures.Bishop;
import ru.vmerkotan.exceptions.ImpossibleMoveException;
import ru.vmerkotan.exceptions.OccupiedWayException;
import ru.vmerkotan.exceptions.FigureNotFoundException;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
* Tests for Board class.
* @author Vadim Merkotan
* @since  1.0
* @version $Id$
*/
public class BoardTest {


	/**
	* Test move method.
	* If no figures are present on the way
	* move should be performed correctly
    * @throws ImpossibleMoveException when figure could not perform such move
    * @throws OccupiedWayException Figure could make such move but other figures
    *         interupts
    * @throws FigureNotFoundException Figure not found on Cell
	*/
    @Test
	public void whenMoveThenNewFigureSHouldBeCreated() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Cell startCell = new Cell(4, 3);
		Bishop bishop = new Bishop(startCell);
		Board board = new Board(new Figure[]{bishop});
		Cell targetCell = new Cell(7, 6);
		boolean moveResult = board.move(startCell, targetCell);
		assertTrue(moveResult);
	}

	/**
	* Test move method.
	* If no figures are found on the source Cell
	* FigureNotFoundException should be thrown
    * @throws ImpossibleMoveException when figure could not perform such move
    * @throws OccupiedWayException Figure could make such move but other figures
    *         interupts
    * @throws FigureNotFoundException Figure not found on Cell
	*/
	@Test(expected = FigureNotFoundException.class)
	public void whenNoFigureFoundThenTrow() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
		Cell startCell = new Cell(4, 2);
		Bishop bishop = new Bishop(new Cell(4, 3));
		Board board = new Board(new Figure[]{bishop});
		Cell targetCell = new Cell(7, 6);
		board.move(startCell, targetCell);
	}

	/**
	* Test move method.
	* If some figures present on the way then
	* OccupiedWayException should be thrown
    * @throws ImpossibleMoveException when figure could not perform such move
    * @throws OccupiedWayException Figure could make such move but other figures
    *         interupts
    * @throws FigureNotFoundException Figure not found on Cell
	*/
	@Test(expected = OccupiedWayException.class)
	public void whenwayOccupiedFoundThenTrow() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
		Cell firstStartCell = new Cell(4, 3);
		Cell secondStartCell = new Cell(5, 4);
		Bishop firstBishop = new Bishop(firstStartCell);
		Bishop secondBishop = new Bishop(secondStartCell);
		Board board = new Board(new Figure[]{firstBishop, secondBishop});
		Cell targetCell = new Cell(7, 6);
		board.move(firstStartCell, targetCell);
	}
}