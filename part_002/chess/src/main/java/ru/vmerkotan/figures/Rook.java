package ru.vmerkotan.figures;

import ru.vmerkotan.exceptions.*;
import ru.vmerkotan.Figure;
import ru.vmerkotan.Cell;

/**
 * The {@code Rook} class represents a descrition of
 * King figure on chess board
 * @since  1.0
 */
public class Rook extends Figure {

    public Rook(Cell position) {
        super(position);
    }

    /*
    * Returns cells on the figure way
    * @param dist Cell destination figure Cell
    * @throw ImpossibleMoveException if Figure can not be moved to destination cell
    */
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell[] result;

        int lengthX = dist.getXvalue() - this.position.getXvalue();
        int lengthY = dist.getYvalue() - this.position.getYvalue();

        if( lengthX != 0 && lengthY != 0) {
            throw new ImpossibleMoveException();
        }

        boolean isHorizontal = lengthY == 0;

        int currentXposition = this.position.getXvalue();
        int currentYposition =  this.position.getYvalue();

        if(isHorizontal) {
            result = new Cell[Math.abs(lengthX)];
        } else {
            result = new Cell[Math.abs(lengthY)];
        }

        for(int i = 0; i < result.length; i++) {
            result[i] = new Cell(
                    lengthX > 0 ? ++currentXposition : lengthY == 0 ? --currentXposition : currentXposition,
                    lengthY > 0 ? ++currentYposition : lengthX == 0 ? --currentYposition : currentYposition);
        }
        return result;
    }

    /*
    * Returns new Rook placed on
    * dist Cell
    * @param dist		Cell where new Rook should be created
    * @return Figure	Rook Figure placed on the dist Cell
    */
    public Figure clone(Cell dist) {
        return new Rook(dist);
    }

}