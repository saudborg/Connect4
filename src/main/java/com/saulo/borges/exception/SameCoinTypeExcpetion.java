package com.saulo.borges.exception;

/**
 * When to players have the same type of coins, expects this excpetion. If thow player put the same coin the system won't work
 * @author sauloborges
 *
 */
public class SameCoinTypeExcpetion extends Exception {

	private static final long serialVersionUID = -3376972678144715020L;
	
	public SameCoinTypeExcpetion() {
		super("The both player have the same type of coin. Please change before start the game");
	}


}
