package ru.vmerkotan.figures;

import org.junit.Test;
import ru.vmerkotan.Cell;
import ru.vmerkotan.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Queen class.
 * @author Vadim Merkotans
 * @since  1.0
 */
public class QueenTest {

    /**
     * Test Queen way method.
     * @throws ImpossibleMoveException when figure could not perform such move
     */
    @Test
    public void whenBishopMoveThenCellsArrayReturn() throws ImpossibleMoveException {
        Cell startCell = new Cell(3, 0);
        Queen b = new Queen(startCell);
        Cell targetCell = new Cell(1, 2);
        Cell[] expectedResult = new Cell[2];
        expectedResult[0] = new Cell(2, 1);
        expectedResult[1] = new Cell(1, 2);
        assertThat(b.way(targetCell), is(expectedResult));
    }

    /**
     * Test Queen way method.
     * @throws ImpossibleMoveException when figure could not perform such move
     */
    @Test
    public void whenRookMoveThenCellsArrayReturn() throws ImpossibleMoveException {
        Cell startCell = new Cell(3, 0);
        Queen b = new Queen(startCell);
        Cell targetCell = new Cell(3, 3);
        Cell[] expectedResult = new Cell[3];
        expectedResult[0] = new Cell(3, 1);
        expectedResult[1] = new Cell(3, 2);
        expectedResult[2] = new Cell(3, 3);
        assertThat(b.way(targetCell), is(expectedResult));
    }

    /**
     * Test Rook way method.
     * If rook could not be moved to target Cell
     * ImpossibleMoveException expected to be thrown
     * @throws ImpossibleMoveException when figure could not perform such move
     */
    @Test(expected = ImpossibleMoveException.class)
    public void testWayWithException()  throws ImpossibleMoveException {
        Cell startCell = new Cell(3, 0);
        Queen b = new Queen(startCell);
        Cell targetCell = new Cell(5, 1);
        b.way(targetCell);
    }
}