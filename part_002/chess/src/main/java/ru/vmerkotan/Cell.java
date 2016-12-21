package ru.vmerkotan;

/**
* The {@code Cell} class represents cell on chess board.
* @since  1.0
*/
public class Cell {
    /**
     * Holds value of x coordinate.
     */
	private int xValue;
    /**
     * Holds value of y coordinate.
     */
	private int yValue;

    /**
     * Constructs new Cell object.
     * @param x coordinate
     * @param y coordinate
     */
	public Cell(int x, int y) {
		this.xValue = x;
		this.yValue = y;
	}

    /**
     * returns value of x coordinate.
     * @return x coordinate
     */
	public int getXvalue() {
		return this.xValue;
	}

    /**
     * returns value of y coordinate.
     * @return y coordinate
     */
	public int getYvalue() {
		return this.yValue;
	}

	/*
	* If target Cell has the same x and y coordinates
	* return true.
	*/
	@Override
	public boolean equals(Object other) {
		if (other == null) {
            return false;
        }
		if (other == this) {
		    return true;
		}
		if (!(other instanceof Cell)) {
            return false;
        }
		Cell otherCell = (Cell) other;
		return this.getXvalue() == otherCell.getXvalue() && this.getYvalue() == otherCell.getYvalue();
	}

    /**
     * returns hash code for Cell object.
     * @return int hascode
     */
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + xValue;
        result = 31 * result + yValue;
        return result;
    }
}