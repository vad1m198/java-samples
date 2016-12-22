package ru.vmerkotan.figures;

import org.junit.Test;
import ru.vmerkotan.Cell;
import ru.vmerkotan.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Rook class.
 * @author Vadim Merkotans
 * @since  1.0
 */
public class RookTest {

    /**
     * Test Rook way method.
     * x destination coordinates incremented
     * @throws ImpossibleMoveException when figure could not perform such move
     */
    @Test
    public void whenWayWithIncrementedXCoordinateThenCellsArrayReturn() throws ImpossibleMoveException {
        Cell startCell = new Cell(0, 0);
        Rook b = new Rook(startCell);
        Cell targetCell = new Cell(3, 0);
        Cell[] expectedResult = new Cell[3];
        expectedResult[0] = new Cell(1, 0);
        expectedResult[1] = new Cell(2, 0);
        expectedResult[2] = new Cell(3, 0);
        assertThat(b.way(targetCell), is(expectedResult));
    }

    /**
     * Test Rook way method.
     * y destination coordinates incremented
     * @throws ImpossibleMoveException when figure could not perform such move
     */
    @Test
    public void whenWayWithIncrementedYCoorThenCellsArrayReturn() throws ImpossibleMoveException {
        Cell startCell = new Cell(0, 0);
        Rook b = new Rook(startCell);
        Cell targetCell = new Cell(0, 3);
        Cell[] expectedResult = new Cell[3];
        expectedResult[0] = new Cell(0, 1);
        expectedResult[1] = new Cell(0, 2);
        expectedResult[2] = new Cell(0, 3);
        assertThat(b.way(targetCell), is(expectedResult));
    }

    /**
     * Test Rook way method.
     * x destination coordinates decremented
     * @throws ImpossibleMoveException when figure could not perform such move
     */
    @Test
    public void whenWayWithDecrementedXCoordinateThenCellsArrayReturn() throws ImpossibleMoveException {
        Cell startCell = new Cell(8, 8);
        Rook b = new Rook(startCell);
        Cell targetCell = new Cell(5, 8);
        Cell[] expectedResult = new Cell[3];
        expectedResult[0] = new Cell(7, 8);
        expectedResult[1] = new Cell(6, 8);
        expectedResult[2] = new Cell(5, 8);
        assertThat(b.way(targetCell), is(expectedResult));
    }

    /**
     * Test Rook way method.
     * y destination coordinates decremented
     * @throws ImpossibleMoveException when figure could not perform such move
     */
    @Test
    public void whenWayWithDecrementedYCoordinateThenCellsArrayReturn() throws ImpossibleMoveException {
        Cell startCell = new Cell(8, 8);
        Rook b = new Rook(startCell);
        Cell targetCell = new Cell(8, 5);
        Cell[] expectedResult = new Cell[3];
        expectedResult[0] = new Cell(8, 7);
        expectedResult[1] = new Cell(8, 6);
        expectedResult[2] = new Cell(8, 5);
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
        Cell startCell = new Cell(0, 0);
        Rook b = new Rook(startCell);
        Cell targetCell = new Cell(1, 1);
        b.way(targetCell);
    }
}