package com.saulo.borges.controller.json;

import java.util.List;

import com.saulo.borges.game.Coin;
import com.saulo.borges.game.Connect4;
import com.saulo.borges.game.PlaceInGrid;
import com.saulo.borges.model.GameModel;
import com.saulo.borges.model.PlayerModel;

/**
 * A json which represent the real status on the game.
 * @author sauloborges
 *
 */
public class ResultGameJson {

	private Integer id;

	private PlayerModel winner;

	private Coin[][] game;

	private List<PlaceInGrid> result;
	
	private String content;

	public ResultGameJson() {
	}

	public ResultGameJson(GameModel game2, Connect4 connect4) {
		this.id = game2.getId();
		this.game = game2.getGame();
		this.winner = connect4.winner;
		this.result = connect4.result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Coin[][] getGame() {
		return game;
	}

	public void setGame(Coin[][] game) {
		this.game = game;
	}

	public PlayerModel getWinner() {
		return winner;
	}

	public void setWinner(PlayerModel winner) {
		this.winner = winner;
	}

	public List<PlaceInGrid> getResult() {
		return result;
	}

	public void setResult(List<PlaceInGrid> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ResultGameJson [id=" + id + ", game=" + this.game + ", winner=" + winner + ", result="
				+ result + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
