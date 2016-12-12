package ru.vmerkotan;

import ru.vmerkotan.exceptions.*;

/**
* The {@code Figure} class represents a behavior of
* abstarct figure on chess board
* @since  1.0
*/
public abstract class Figure {
	protected final Cell position;
	
	public Figure(Cell position) {
		this.position = position;
	}
	
	/*
	* Returns cells on the figure way
	* @param dist Cell destination figure Cell
	* @throw ImposibleMoveException if Figure can not be moved to destination cell
	*/
	public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;
	
	/*
	* Returns new Figure placed on dist Cell
	* 
	* @param dist		Cell where nre Bishop should be created
	* @return Figure	Figure object placed on the dist Cell
	*/	
	public abstract Figure clone(Cell dist);
}