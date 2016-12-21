package ru.vmerkotan;

import ru.vmerkotan.exceptions.ImpossibleMoveException;

/**
* The {@code Figure} class represents a behavior of
* abstarct figure on chess board.
* @since  1.0
*/
public abstract class Figure {
    /**
     * Holds current figure Cell position.
     */
	private final Cell position;

    /**
     * Constructs new Figure.
     * @param position Cell to place figure to
     */
	public Figure(Cell position) {
		this.position = position;
	}

    /**
     * returns current position.
     * @return Cell figure position
     */
    public Cell getPosition() {
        return this.position;
    }

    /**
     * Returns cells on the figure way.
     * @param dist Cell destination figure Cell
     * @return Cell[] which figure should walk through
     * @throws ImpossibleMoveException if Figure can not be moved to destination cell
     */
	public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * Returns new Figure placed on dist Cell.
     *
     * @param dist		Cell where nre Bishop should be created
     * @return Figure	Figure object placed on the dist Cell
     */
	public abstract Figure clone(Cell dist);
}