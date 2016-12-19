package ru.vmerkotan.figures;

import org.junit.Test;
import ru.vmerkotan.Cell;
import ru.vmerkotan.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for Knight class
 *
 * @author Vadim Merkotans
 * @since  1.0
 */
public class KnightTest {

    /**
     * Test Knight way method
     */
    @Test
    public void whenIncrementedXCoordinateThenCellsArrayReturn() throws ImpossibleMoveException {
        Cell startCell = new Cell(1,0);
        Knight b = new Knight(startCell);
        Cell targetCell = new Cell(3,2);
        Cell[] expectedResult = new Cell[1];
        expectedResult[0] = new Cell(3,2);
        assertThat(b.way(targetCell), is(expectedResult));
    }

    /**
     * Test Rook way method
     * If rook could not be moved to target Cell
     * ImpossibleMoveException expected to be thrown
     */
    @Test(expected = ImpossibleMoveException.class)
    public void testWayWithException()  throws ImpossibleMoveException{
        Cell startCell = new Cell(1,0);
        Knight b = new Knight(startCell);
        Cell targetCell = new Cell(1,1);
        b.way(targetCell);
    }

}