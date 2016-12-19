package ru.vmerkotan.figures;

import ru.vmerkotan.exceptions.*;
import ru.vmerkotan.Figure;
import ru.vmerkotan.Cell;

/**
 * The {@code Knight} class represents a descrition of
 * King figure on chess board
 * @since  1.0
 */
public class Knight extends Figure {

    public Knight(Cell position) {
        super(position);
    }

    /*
    * Returns cells on the figure way
    * @param dist Cell destination figure Cell
    * @throw ImpossibleMoveException if Figure can not be moved to destination cell
    */
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result;

        int lengthX = Math.abs(dest.getXvalue() - this.position.getXvalue());
        int lengthY = Math.abs(dest.getYvalue() - this.position.getYvalue());

        if( (lengthX != 2 && lengthY != 1) || (lengthX != 1 && lengthY != 2) ) {
            throw new ImpossibleMoveException();
        }

        result = new Cell[1];
        result[0] = dest;

        return result;

    }

    /*
    * Returns new Knight placed on
    * dist Cell
    * @param dist		Cell where new Knight should be created
    * @return Figure	Knight Figure placed on the dist Cell
    */
    public Figure clone(Cell dist) {
        return new Knight(dist);
    }

}