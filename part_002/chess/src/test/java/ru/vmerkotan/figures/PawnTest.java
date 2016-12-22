package ru.vmerkotan.figures;

import org.junit.Test;
import ru.vmerkotan.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Pawn class.
 * @author Vadim Merkotan
 * @since  1.0
 * @version $Id$
 */
public class PawnTest {

    /**
     * Test Pawn way method.
     * @throws ImpossibleMoveException when figure could not perform such move
     */
    @Test
    public void whenPassPossibleDestinationThenReturnWayCells() throws ImpossibleMoveException {
        Cell startCell = new Cell(0, 1);
        Pawn b = new Pawn(startCell);
        Cell targetCell = new Cell(0, 2);
        Cell[] expectedResult = new Cell[1];
        expectedResult[0] = new Cell(0, 2);
        assertThat(b.way(targetCell), is(expectedResult));
    }

    /**
     * Test Pawn way method.
     * If pawn could not be moved to target Cell
     * ImpossibleMoveException expected to be thrown
     * @throws ImpossibleMoveException when figure could not perform such move
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenPassImPossibleDestinationThenThrow() throws ImpossibleMoveException {
        Cell startCell = new Cell(0, 1);
        Pawn b = new Pawn(startCell);
        Cell targetCell = new Cell(1, 1);
        b.way(targetCell);
    }
}
