package ru.vmerkotan.figures;

import ru.vmerkotan.exceptions.ImpossibleMoveException;
import ru.vmerkotan.Figure;
import ru.vmerkotan.Cell;

/**
 * The {@code Pawn} class represents a descrition of
 * Pawn figure on chess board.
 * @since  1.0
 */
public class Pawn extends Figure {

    /**
     * Constructs new Pawn object.
     * @param position Cell to place new Pawn object to.
     */
    public Pawn(Cell position) {
        super(position);
    }

    /**
    * Returns cells on the figure way.
    * @param dist Cell destination figure Cell
    * @throws ImpossibleMoveException if Figure can not be moved to destination cell
    * @return Cell[] Figure should pass through
    */
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell[] result;
        if (this.position.getXvalue() != dist.getXvalue() || Math.abs(this.position.getYvalue() - dist.getYvalue()) > 1) {
            throw new ImpossibleMoveException();
        }
        result = new Cell[1];
        result[0] = new Cell(
                dist.getXvalue(),
                dist.getYvalue()
        );
        return result;
    }

    /**
    * Returns new Pawn placed on
    * dist Cell.
    * @param dist		Cell where new Pawn should be created
    * @return Figure	Pawn Figure placed on the dist Cell
    */
    public Figure clone(Cell dist) {
        return new Pawn(dist);
    }

}