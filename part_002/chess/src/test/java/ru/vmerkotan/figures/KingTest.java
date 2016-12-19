package ru.vmerkotan.figures;

import org.junit.Test;
import ru.vmerkotan.Cell;
import ru.vmerkotan.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * Tests for King class
 *
 * @author Vadim Merkotan
 * @since  1.0
 * @version $Id$
 */
public class KingTest {

    /**
     * Test King way method
     */
    @Test
    public void whenPassPossibleDestinationThenReturnWayCells() throws ImpossibleMoveException {
        Cell startCell = new Cell(0,1);
        King b = new King(startCell);
        Cell targetCell = new Cell(0,2);
        Cell[] expectedResult = new Cell[1];
        expectedResult[0] = new Cell(0,2);
        assertThat(b.way(targetCell), is(expectedResult));
    }

    /**
     * Test King way method
     * If king could not be moved to target Cell
     * ImpossibleMoveException expected to be thrown
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenPassImPossibleDestinationThenThrow() throws ImpossibleMoveException {
        Cell startCell = new Cell(0,1);
        King b = new King(startCell);
        Cell targetCell = new Cell(2,1);
        b.way(targetCell);
    }

}