package com.saulo.borges.controller.json;

import java.io.Serializable;

/**
 * Json which represents a new move on the game
 * @author sauloborges
 *
 */
public class PlayGameJson implements Serializable {

	private static final long serialVersionUID = -303355571008476108L;

	private String col;

	private String game;

	private String player;

	public PlayGameJson() {
	}

	public PlayGameJson(String col, String game, String player) {
		super();
		this.col = col;
		this.game = game;
		this.player = player;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

}
