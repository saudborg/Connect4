package com.saulo.borges.game;

import java.io.Serializable;

/**
 * This class represents one place in the grid (array[][]).
 * 
 * The class was created to help when show the results of the winner
 * @author sauloborges
 *
 */
public class PlaceInGrid implements Serializable{
	
	private static final long serialVersionUID = 1320225752940569620L;

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
