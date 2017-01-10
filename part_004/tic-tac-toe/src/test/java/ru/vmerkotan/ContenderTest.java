package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * {@code ContenderTest} class represents
 * tests for Contender class.
 *
 * Created by vmerkotan on 1/10/2017.
 */
public class ContenderTest {

    /**
     * test getMove point.
     * If free cells are present then return
     * free cell.
     */
    @Test
    public void whenFreeCellsArePresentThenGetMoveReturnPoint() {
        GameField gf = new GameField(3);
        gf.makeMove(new Point(0, 0), "X");
        Contender contender = new Contender(gf);

        Point result = contender.getMove();

        assertThat(result.getX(), is(1));
        assertThat(result.getY(), is(0));
    }

    /**
     * test getMove point.
     * If all cells are occupied then throw.
     * free cell.
     */
    @Test(expected = RuntimeException.class)
    public void whenAllCellsAreOccupiedThenThrow() {
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
        Contender contender = new Contender(gf);

        contender.getMove();
    }

}