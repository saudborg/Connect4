package com.saulo.borges.game;

import static com.saulo.borges.game.GameSettings.GAME_COL;
import static com.saulo.borges.game.GameSettings.GAME_ROW;

import java.util.ArrayList;
import java.util.List;

import com.saulo.borges.exception.InvalidCollunmExcepetion;
import com.saulo.borges.exception.PlayerNullExcpetion;
import com.saulo.borges.exception.SameCoinTypeExcpetion;
import com.saulo.borges.model.GameModel;
import com.saulo.borges.model.PlayerModel;

public class Connect4 {
	
	public PlayerModel winner;

	public List<PlaceInGrid> result;
	
	private GameModel game;

	public Connect4() {
		game = new GameModel(null, null, false, 0);
		game.setGame(new Coin[GAME_ROW][GAME_COL]);
	}
	
	public Connect4(GameModel game) {
		this.game = game;
		result = new ArrayList<PlaceInGrid>();
	}

	public Connect4(int row, int col) {
		game = new GameModel(null, null, false, 0);
		game.setGame(new Coin[row][col]);
	}

	public void newGame(PlayerModel player1, PlayerModel player2) throws SameCoinTypeExcpetion, PlayerNullExcpetion {
		if(player1 == null || player2 == null){
			throw new PlayerNullExcpetion();
		}
		
		if (player1.getCoin().getValue().equals(player2.getCoin().getValue())) {
			throw new SameCoinTypeExcpetion();
		}

		game.setPlayer1(player1);
		game.setPlayer2(player2);
		
		game.setPlayerTurn(player1);

		result = new ArrayList<PlaceInGrid>();

		initializeGame();
		
	}

	/**
	 * Fill the game with spaces
	 */
	private void initializeGame() {
		game.setTotalCoinsInGrid(0);
		for (int row = 0; row < game.getGame().length; row++) {
			for (int col = 0; col < game.getGame()[0].length; col++) {
				game.getGame()[row][col] = Coin.SPACE;
			}
		}
	}

	public boolean dropCoin(Integer play, PlayerModel player) throws InvalidCollunmExcepetion {
		if(play > game.getGame()[0].length - 1){
			throw new InvalidCollunmExcepetion(play);
		}
		if (!game.getGame()[0][play].equals(Coin.SPACE)) {
			throw new InvalidCollunmExcepetion(play);
		}

		for (int row = game.getGame().length - 1; row >= 0; row--) {
			if (game.getGame()[row][play].equals(Coin.SPACE)) {
				game.getGame()[row][play] = player.getCoin();

				game.setTotalCoinsInGrid(game.getTotalCoinsInGrid() + 1);
				hasAWinner();
				return true;
			}
		}

		return false;

	}

	public void hasAWinner() {

		boolean findInRows = findInRows();
		boolean findInCol = findInCol();
		boolean findInDiagonalUp = findInDiagonalUp();
		boolean findInDiagonalDown = findInDiagonalDown();

		if (findInRows || findInCol || findInDiagonalUp || findInDiagonalDown)
			game.setFinishedGame(true);

		if (game.isFinishedGame()) {
			PlaceInGrid placeInGrid = result.get(0);
			Coin coin = game.getGame()[placeInGrid.getRow()][placeInGrid.getCol()];
			if (game.getPlayer1().getCoin().equals(coin))
				winner = game.getPlayer1();
			else
				winner = game.getPlayer2();
		} else {
			if(game.getPlayerTurn().getId().equals(game.getPlayer1().getId())){
				game.setPlayerTurn(game.getPlayer2());
			} else {
				game.setPlayerTurn(game.getPlayer1());
			}
		}

		if (game.getTotalCoinsInGrid() >= GAME_ROW * GAME_COL) {
			game.setFinishedGame(true);
			winner = null;
		}
	}

	private boolean findInDiagonalDown() {
		for (int row = 0; row < game.getGame().length - 3; row++) {
			for (int col = 0; col < game.getGame()[0].length - 3; col++) {
				if (!game.getGame()[row][col].equals(Coin.SPACE)) {
					if (game.getGame()[row][col].equals(game.getGame()[row + 1][col + 1]) //
							&& game.getGame()[row][col].equals(game.getGame()[row + 2][col + 2])//
							&& game.getGame()[row][col].equals(game.getGame()[row + 3][col + 3])) {

						result.add(new PlaceInGrid(row, col));
						result.add(new PlaceInGrid(row + 1, col + 1));
						result.add(new PlaceInGrid(row + 2, col + 2));
						result.add(new PlaceInGrid(row + 3, col + 3));

						System.out.println("findInDiagonalDown");
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean findInDiagonalUp() {
		for (int row = game.getGame().length - 1; row >= 3; row--) {
			for (int col = 0; col < game.getGame()[0].length - 3; col++) {
				if (!game.getGame()[row][col].equals(Coin.SPACE)) {
					if (game.getGame()[row][col].equals(game.getGame()[row - 1][col + 1]) //
							&& game.getGame()[row][col].equals(game.getGame()[row - 2][col + 2])
							&& game.getGame()[row][col].equals(game.getGame()[row - 3][col + 3])) {

						result.add(new PlaceInGrid(row, col));
						result.add(new PlaceInGrid(row - 1, col + 1));
						result.add(new PlaceInGrid(row - 2, col + 2));
						result.add(new PlaceInGrid(row - 3, col + 3));

						System.out.println("findInDiagonalUp");
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean findInCol() {
		for (int col = 0; col <= game.getGame()[0].length - 1; col++) {
			int count = 1;
			for (int row = game.getGame().length - 1; row > 0; row--) {
				if (!game.getGame()[row][col].equals(Coin.SPACE)) {
					if (game.getGame()[row][col].equals(game.getGame()[row - 1][col])) {
						count++;
					} else {
						count = 1;
					}

					if (count == 4) {
						result.add(new PlaceInGrid(row - 1, col));
						result.add(new PlaceInGrid(row, col));
						result.add(new PlaceInGrid(row + 1, col));
						result.add(new PlaceInGrid(row + 2, col));

						System.out.println("findInCol");
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean findInRows() {
		for (int row = game.getGame().length - 1; row >= 0; row--) {
			int count = 1;
			for (int col = 0; col < game.getGame()[0].length - 1; col++) {
				if (!game.getGame()[row][col].equals(Coin.SPACE)) {
					if (game.getGame()[row][col].equals(game.getGame()[row][col + 1])) {
						count++;
					} else {
						count = 1;
					}

					if (count == 4) {
						result.add(new PlaceInGrid(row, col - 2));
						result.add(new PlaceInGrid(row, col - 1));
						result.add(new PlaceInGrid(row, col));
						result.add(new PlaceInGrid(row, col + 1));

						System.out.println("findInRows");
						return true;
					}
				}
			}
		}
		return false;
	}

	public GameModel getGame() {
		return game;
	}

	public void setGame(GameModel game) {
		this.game = game;
	}

}
