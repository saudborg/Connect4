package com.saulo.borges;

public class SameCoinTypeExcpetion extends Exception {

	private static final long serialVersionUID = -3376972678144715020L;
	
	public SameCoinTypeExcpetion() {
		super("The both player have the same type of coin. Please change before start the game");
	}


}
