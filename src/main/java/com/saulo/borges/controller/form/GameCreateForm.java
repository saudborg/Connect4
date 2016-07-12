package com.saulo.borges.controller.form;

import java.io.Serializable;

/**
 * Represents a form to create a request to create a new game
 * @author sauloborges
 *
 */
public class GameCreateForm implements Serializable{
	
	private static final long serialVersionUID = -4797346425139089160L;

	private Integer player1;
	
	private Integer player2;

	public Integer getPlayer1() {
		return player1;
	}

	public void setPlayer1(Integer player1) {
		this.player1 = player1;
	}

	public Integer getPlayer2() {
		return player2;
	}

	public void setPlayer2(Integer player2) {
		this.player2 = player2;
	}

	public GameCreateForm(Integer player1, Integer player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public GameCreateForm() {
	}
	
	

}
