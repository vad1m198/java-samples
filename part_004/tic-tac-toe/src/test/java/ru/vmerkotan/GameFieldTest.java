package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * {@code GameFieldTest} class represents
 * tests for GameField class.
 *
 * Created by vmerkotan on 1/10/2017.
 */
public class GameFieldTest {
    /**
     * Verify game field error on creation.
     * If passed size is less then 3
     * then throw new RunTimeException.
     */
    @Test(expected = RuntimeException.class)
    public void whenSizeIsLess3ThenThrow() {
        GameField gf = new GameField(2);
    }
    /**
     * Verify game field error on creation.
     * If passed size  is even
     * then throw new RunTimeException.
     */
    @Test(expected = RuntimeException.class)
    public void whenSizeIsEvenThenThrow() {
        GameField gf = new GameField(4);
    }
    /**
     * When make move invoked for free cell then
     * verify occupied should return false.
     */
    @Test
    public void whenVerifyOccupiedForOccupiedCellThenReturnTrue() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(0, 0), "X");
        assertTrue(gf.verifyOccupied(0, 0));
    }

    /**
     * When verify occupied for free cell
     * then return false.
     */
    @Test
    public void whenVerifyOccupiedForFreeCellThenReturnFalse() {
        GameField gf = new GameField(3);
        assertFalse(gf.verifyOccupied(0, 0));
    }

    /**
     * Verify getSize method.
     * Should return field size.
     */
    @Test
    public void whenGetSizeThenReturnFieldSize() {
        GameField gf = new GameField(3);
        assertThat(gf.getSize(), is(3));
    }

    /**
     * Verify makemove method.
     * when make move to occupied Cell then throw.
     */
    @Test(expected = RuntimeException.class)
    public void whenMakeMoveToOccupiedThenThrow() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(0, 0), "X");
        gf.makeMove(new Point(0, 0), "X");
    }

    /**
     * Verify makemove method.
     * when make move to Cell which is out of field
     * then throw.
     */
    @Test(expected = RuntimeException.class)
    public void whenMakeMoveToOutCellThenThrow() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(4, 4), "X");
    }

    /**
     * Verify isWinner method.
     * if horizontal line contains the same signs
     * then return true
     */
    @Test
    public void whenHorizontalLineHasSameSignsThenTrue() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(0, 1), "X");
        gf.makeMove(new Point(1, 1), "X");
        gf.makeMove(new Point(2, 1), "X");
        assertTrue(gf.isWinner());
    }

    /**
     * Verify isWinner method.
     * if vertical line contains the same signs
     * then return true
     */
    @Test
    public void whenVerticalLineHasSameSignsThenTrue() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(1, 0), "X");
        gf.makeMove(new Point(1, 1), "X");
        gf.makeMove(new Point(1, 2), "X");
        assertTrue(gf.isWinner());
    }

    /**
     * Verify isWinner method.
     * if diagonal line contains the same signs
     * then return true
     */
    @Test
    public void whenDiagonalLineHasSameSignsThenTrue() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(0, 0), "X");
        gf.makeMove(new Point(1, 1), "X");
        gf.makeMove(new Point(2, 2), "X");
        assertTrue(gf.isWinner());
    }

    /**
     * Verify isWinner method.
     * if reverse diagonal line contains the same signs
     * then return true
     */
    @Test
    public void whenReverseDiagonalLineHasSameSignsThenTrue() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(0, 2), "X");
        gf.makeMove(new Point(1, 1), "X");
        gf.makeMove(new Point(2, 0), "X");
        assertTrue(gf.isWinner());
    }

    /**
     * Verify isWinner method.
     * if no line present then return false.
     */
    @Test
    public void whenNoLineHasSameSignsThenFalse() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(0, 0), "X");
        gf.makeMove(new Point(0, 1), "X");
        assertFalse(gf.isWinner());
    }

    /**
     * Verify isFull method.
     * if some cells are free then return false.
     */
    @Test
    public void whenSomeCellsFreeThenFalse() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(0, 0), "X");
        assertFalse(gf.isFull());
    }

    /**
     * Verify isFull method.
     * if all cells are occupied with signs the return false.
     */
    @Test
    public void whenAllCellsOccupiedThenTrue() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(0, 0), "X");
        gf.makeMove(new Point(0, 1), "X");
        gf.makeMove(new Point(0, 2), "X");
        gf.makeMove(new Point(1, 0), "X");
        gf.makeMove(new Point(1, 1), "X");
        gf.makeMove(new Point(1, 2), "X");
        gf.makeMove(new Point(2, 0), "X");
        gf.makeMove(new Point(2, 1), "X");
        gf.makeMove(new Point(2, 2), "X");
        assertTrue(gf.isFull());
    }

}