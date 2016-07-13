package com.saulo.borges.exception;

/**
 * When the player try to put a coin in a Column in an invalid column, like out
 * of bound of the array or the column is full, the system should throw this
 * excpetion
 * 
 * @author sauloborges
 *
 */
public class InvalidColumnExcepetion extends Exception {

	private static final long serialVersionUID = -4768623310291540088L;

	public InvalidColumnExcepetion(Integer col) {
		super("The column " + col + " is invalid. Try another one");
	}

}
