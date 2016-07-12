package com.saulo.borges.exception;

public class InvalidCollunmExcepetion extends Exception {

	private static final long serialVersionUID = -4768623310291540088L;
	
	public InvalidCollunmExcepetion(Integer col) {
		super("The collunm " + col + " is invalid. Try another one");
	}

}
