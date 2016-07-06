package com.saulo.borges;

public class PlaceInGrid {
	
	public int row;
	
	public int col;
	
	public PlaceInGrid(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	@Override
	public String toString() {
		return "["+ row + "," + col + "]";
	}


}
