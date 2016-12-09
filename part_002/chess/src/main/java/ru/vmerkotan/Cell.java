package ru.vmerkotan;

/**
* The {@code Cell} class represents cell on chess board
* @since  1.0
*/
public class Cell {
	private int xValue;
	private int yValue;	
	
	public Cell(int x, int y) {
		this.xValue = x;
		this.yValue = y;
	}
	
	/*
	* returns value of x coordinate
	*/
	public int getXvalue() {
		return this.xValue;
	}
	
	/*
	* returns value of y coordinate
	*/
	public int getYvalue() {
		return this.yValue;
	}
	
	/*
	* If target Cell has the same x and y coordinates
	* reutrn true
	*/
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Cell))return false;
		Cell otherCell = (Cell)other;		
		return this.getXvalue() == otherCell.getXvalue() && this.getYvalue() == otherCell.getYvalue();
	}
}