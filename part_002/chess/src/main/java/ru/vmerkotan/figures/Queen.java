package ru.vmerkotan.figures;


import ru.vmerkotan.Cell;
import ru.vmerkotan.Figure;
import ru.vmerkotan.exceptions.ImpossibleMoveException;

/**
 * The {@code Queen} class represents a descrition of
 * King figure on chess board
 * @since  1.0
 */
public class Queen extends Figure {
    private Bishop bishop;
    private Rook rook;
    public Queen(Cell position) {
        super(position);
        bishop = new Bishop(position);
        rook = new Rook(position);
    }

    /*
    * Returns cells on the figure way
    * @param dist Cell destination figure Cell
    * @throw ImpossibleMoveException if Figure can not be moved to destination cell
    */
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result = null;
        boolean canBishopMove = true;
        boolean canRookMove = true;
        try {
            result = rook.way(dest);
        } catch (ImpossibleMoveException imv) {
            canBishopMove = false;
        }

        try {
            result = bishop.way(dest);
        } catch (ImpossibleMoveException imv) {
            canRookMove = false;
        }

        if(!canBishopMove && !canRookMove) {
            throw new ImpossibleMoveException();
        }

        return result;

    }

    /*
    * Returns new Queen placed on
    * dist Cell
    * @param dist		Cell where new Queen should be created
    * @return Figure	Queen Figure placed on the dist Cell
    */
    public Figure clone(Cell dist) {
        return new Queen(dist);
    }

}
