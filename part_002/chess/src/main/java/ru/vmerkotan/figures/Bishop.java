package ru.vmerkotan.figures;

import ru.vmerkotan.exceptions.*;
import ru.vmerkotan.Figure;
import ru.vmerkotan.Cell;
import java.lang.*;

/**
* The {@code Bishop} class represents a descrition of
* Bishop figure on chess board
* @since  1.0
*/
public class Bishop extends Figure{
	
	public Bishop(Cell position) {
		super(position);
	}
	
	/*
	* Returns cells on the figure way
	* @param dist Cell destination figure Cell
	* @throw ImpossibleMoveException if Figure can not be moved to destination cell
	*/
	public Cell[] way(Cell dist) throws ImpossibleMoveException {
		Cell[] result;
		
		if(Math.abs(this.position.getXvalue() - dist.getXvalue()) !=
			Math.abs(this.position.getYvalue() - dist.getYvalue())) {
			throw new ImpossibleMoveException();
		}
		
		result = new Cell[Math.abs(this.position.getXvalue() - dist.getXvalue())];
				
		boolean incrementX = dist.getXvalue() - this.position.getXvalue() > 0;
		boolean incrementY = dist.getYvalue() - this.position.getYvalue() > 0;
		
		int currentXposition = incrementX ? this.position.getXvalue() + 1 : this.position.getXvalue() - 1;
		int currentYposition = incrementY ? this.position.getYvalue() + 1 : this.position.getYvalue() - 1;
		
		for(int i = 0; i < result.length; i++) {
			result[i] = new Cell(
			incrementX ? currentXposition++ : currentXposition--, 
			incrementY ? currentYposition++ : currentYposition--);
		}
		return result;
	}
	
	/*
	* Returns new Bishop placed on
	* dist Cell
	* @param dist		Cell where nre Bishop should be created
	* @return Figure	Bishop Figure placed on the dist Cell
	*/
	public Figure clone(Cell dist) {
		return new Bishop(dist);
	}

}