package com.saulo.borges.controller.form;

import java.io.Serializable;

/**
 * Represents a form to create a request to a new move
 * 
 * @author sauloborges
 *
 */
public class MoveForm implements Serializable {

	private static final long serialVersionUID = 4656819296375710542L;

	private Integer playerId;

	private Integer col;

	public MoveForm() {
	}

	public MoveForm(Integer playerId, Integer col) {
		super();
		this.playerId = playerId;
		this.col = col;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}

}
