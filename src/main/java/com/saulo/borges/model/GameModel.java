package com.saulo.borges.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.saulo.borges.game.Coin;
import com.saulo.borges.game.PlaceInGrid;

/**
 * This class stores in the database the status of the game, its players, if the
 * game is finished and the who is the next turn
 * 
 * @author sauloborges
 *
 */
@Entity
public class GameModel implements Serializable {

	private static final long serialVersionUID = 274665280214947230L;

	@Id
	@GeneratedValue
	private Integer id;

	@Lob
	@Column(length = 10000)
	private Coin[][] game;

	@Lob
	@Column(length = 10000)
	private PlayerModel player1;

	@Lob
	@Column(length = 10000)
	private PlayerModel player2;

	private boolean finishedGame = false;

	@Transient
	private List<PlaceInGrid> result;

	private int totalCoinsInGrid = 0;

	@Lob
	@Column(length = 10000)
	private PlayerModel playerTurn;

	public GameModel(Coin[][] game, PlayerModel player1, PlayerModel player2, boolean finishedGame,
			List<PlaceInGrid> result, int totalCoinsInGrid, PlayerModel playerTurn) {
		super();
		this.game = game;
		this.player1 = player1;
		this.player2 = player2;
		this.finishedGame = finishedGame;
		this.result = result;
		this.totalCoinsInGrid = totalCoinsInGrid;
		this.playerTurn = playerTurn;
	}

	public GameModel(PlayerModel player1, PlayerModel player2, boolean finishedGame, int totalCoinsInGrid) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.finishedGame = finishedGame;
		this.totalCoinsInGrid = totalCoinsInGrid;
	}

	public GameModel() {
		this.finishedGame = false;
		this.totalCoinsInGrid = 0;
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

	@OneToOne
	public PlayerModel getPlayer1() {
		return player1;
	}

	public void setPlayer1(PlayerModel player1) {
		this.player1 = player1;
	}

	@OneToOne
	public PlayerModel getPlayer2() {
		return player2;
	}

	public void setPlayer2(PlayerModel player2) {
		this.player2 = player2;
	}

	public boolean isFinishedGame() {
		return finishedGame;
	}

	public void setFinishedGame(boolean finishedGame) {
		this.finishedGame = finishedGame;
	}

	public List<PlaceInGrid> getResult() {
		return result;
	}

	public void setResult(List<PlaceInGrid> result) {
		this.result = result;
	}

	public int getTotalCoinsInGrid() {
		return totalCoinsInGrid;
	}

	public void setTotalCoinsInGrid(int totalCoinsInGrid) {
		this.totalCoinsInGrid = totalCoinsInGrid;
	}

	@OneToOne
	public PlayerModel getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(PlayerModel playerTurn) {
		this.playerTurn = playerTurn;
	}

}
