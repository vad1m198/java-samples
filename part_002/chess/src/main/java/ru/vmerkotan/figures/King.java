package ru.vmerkotan.figures;

import ru.vmerkotan.exceptions.*;
import ru.vmerkotan.Figure;
import ru.vmerkotan.Cell;
import java.lang.*;

/**
 * The {@code King} class represents a descrition of
 * King figure on chess board
 * @since  1.0
 */
public class King extends Figure{

    public King(Cell position) {
        super(position);
    }

    /*
    * Returns cells on the figure way
    * @param dist Cell destination figure Cell
    * @throw ImpossibleMoveException if Figure can not be moved to destination cell
    */
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell[] result;

        if(Math.abs(this.position.getXvalue() - dist.getXvalue()) > 1 || Math.abs(this.position.getYvalue() - dist.getYvalue()) > 1) {
            throw new ImpossibleMoveException();
        }

        result = new Cell[1];
        result[0] = new Cell (
                dist.getXvalue(),
                dist.getYvalue()
        );
        return result;
    }

    /*
    * Returns new King placed on
    * dist Cell
    * @param dist		Cell where new King should be created
    * @return Figure	King Figure placed on the dist Cell
    */
    public Figure clone(Cell dist) {
        return new King(dist);
    }

}