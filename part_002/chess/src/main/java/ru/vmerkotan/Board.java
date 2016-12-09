package ru.vmerkotan;

import ru.vmerkotan.exceptions.*;

/**
* The {@code Board} class represents chess board
* @since  1.0
*/
public class Board {
	
	private Figure[] figures;
	
	public Board(Figure[] figures) {
		this.figures = figures;
	}
	
	/**
	* Moves figure from source to dist Cell
	* @param source	Cell to move figure from
	* @param dist	Cell to move figure to
	* @throw ImpossibleMoveException if figure can not move in that way
	* @throw OccupiedWayException if figure can not move to dist because of
								  other figures impede еру ьщму
	* @throw FigureNotFoundException if no figures found on dist  Cell
	*/
	boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {		
		boolean figureExist = false;
		Figure sourceFigure = null;
		int sourceFigurePosition = -1;
		for(int i = 0; i < this.figures.length; i++) {
			if(this.figures[i].position.equals(source)) {
				figureExist = true;
				sourceFigure = this.figures[i];
				sourceFigurePosition = i;
				break;
			}
		}
			
		
		if(!figureExist) {
			throw new FigureNotFoundException();
		}
		
		Cell[] wayCells = sourceFigure.way(dist);
		for(Cell c: wayCells) {
			for(Figure f: this.figures) {
				if(f.position.equals(c)) {
					throw new OccupiedWayException();					
				}
			}
		}
		
		this.figures[sourceFigurePosition] = sourceFigure.clone(dist);
		return true;
	}
	
	
}

