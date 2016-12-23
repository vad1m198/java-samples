package ru.vmerkotan.figures;

import ru.vmerkotan.exceptions.ImpossibleMoveException;
import ru.vmerkotan.Figure;
import ru.vmerkotan.Cell;

/**
* The {@code Bishop} class represents a descrition of
* Bishop figure on chess board.
* @since  1.0
*/
public class Bishop extends Figure {
	/**
	 * Constructs new Bishop object.
	 * @param position Cell current Bishop position
	 */
	public Bishop(Cell position) {
		super(position);
	}

	/**
	* Returns cells on the figure way.
	* @param dist Cell destination figure Cell
	* @throws ImpossibleMoveException if Figure can not be moved to destination cell
    * @return Cell[] all cells figure should pass
	*/
	public Cell[] way(Cell dist) throws ImpossibleMoveException {
		Cell[] result;
		if (Math.abs(this.getPosition().getXvalue() - dist.getXvalue())
                != Math.abs(this.getPosition().getYvalue() - dist.getYvalue())) {
			throw new ImpossibleMoveException();
		}
		result = new Cell[Math.abs(this.getPosition().getXvalue() - dist.getXvalue())];
		boolean incrementX = dist.getXvalue() - this.getPosition().getXvalue() > 0;
		boolean incrementY = dist.getYvalue() - this.getPosition().getYvalue() > 0;
		int currentXposition = incrementX ? this.getPosition().getXvalue() + 1 : this.getPosition().getXvalue() - 1;
		int currentYposition = incrementY ? this.getPosition().getYvalue() + 1 : this.getPosition().getYvalue() - 1;
		for (int i = 0; i < result.length; i++) {
			result[i] = new Cell(
			incrementX ? currentXposition++ : currentXposition--,
			incrementY ? currentYposition++ : currentYposition--);
		}
		return result;
	}

	/**
	* Returns new Bishop placed on
	* dist Cell.
	* @param dist		Cell where nre Bishop should be created
	* @return Figure	Bishop Figure placed on the dist Cell
	*/
	public Figure clone(Cell dist) {
		return new Bishop(dist);
	}
}