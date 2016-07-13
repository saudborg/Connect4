package com.saulo.borges.game;

import static com.saulo.borges.game.GameSettings.GAME_COL;
import static com.saulo.borges.game.GameSettings.GAME_ROW;

import java.util.ArrayList;
import java.util.List;

import com.saulo.borges.exception.InvalidColumnExcepetion;
import com.saulo.borges.exception.PlayerNullExcpetion;
import com.saulo.borges.exception.SameCoinTypeExcpetion;
import com.saulo.borges.model.GameModel;
import com.saulo.borges.model.PlayerModel;

/**
 * This class represents the rules of the game Connect 4
 * 
 * @author sauloborges
 *
 */
public class Connect4 {

	public PlayerModel winner;

	// List of the places where the winner has coins
	public List<PlaceInGrid> result;

	private GameModel game;

	/**
	 * Constructor default, which you don't have to choose anything. Will be
	 * create a default game
	 */
	public Connect4() {
		game = new GameModel(null, null, false, 0);
		game.setGame(new Coin[GAME_ROW][GAME_COL]);
	}

	/**
	 * Constructor when you already have a game, and decide to continue
	 * 
	 * @param game
	 */
	public Connect4(GameModel game) {
		this.game = game;
		result = new ArrayList<PlaceInGrid>();
	}

	/**
	 * Constructor when you would like to have a different type of game, like
	 * 10x10
	 * 
	 * @param row
	 * @param col
	 */
	public Connect4(int row, int col) {
		game = new GameModel(null, null, false, 0);
		game.setGame(new Coin[row][col]);
	}

	/**
	 * Responsible to initialize the game. Verify if the both player can play
	 * with each other
	 * 
	 * @param player1
	 * @param player2
	 * @throws SameCoinTypeExcpetion
	 * @throws PlayerNullExcpetion
	 */
	public void newGame(PlayerModel player1, PlayerModel player2) throws SameCoinTypeExcpetion, PlayerNullExcpetion {
		if (player1 == null || player2 == null) {
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

	/**
	 * Responsible to do the play. After get the correct position, change for
	 * the coin of the player, and verify if the game has winner or not yet
	 * 
	 * @param play
	 * @param player
	 * @return
	 * @throws InvalidColumnExcepetion
	 */
	public boolean dropCoin(Integer play, PlayerModel player) throws InvalidColumnExcepetion {
		if (play > game.getGame()[0].length - 1) {
			throw new InvalidColumnExcepetion(play);
		}
		if (!game.getGame()[0][play].equals(Coin.SPACE)) {
			throw new InvalidColumnExcepetion(play);
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

	/**
	 * Verify if the game has 4 coins connected. If yes, finished the game.
	 */
	public void hasAWinner() {

		boolean findInRows = findInRows();
		boolean findInCol = findInCol();
		boolean findInDiagonalUp = findInDiagonalUp();
		boolean findInDiagonalDown = findInDiagonalDown();

		// If find at least one, the game is finished
		if (findInRows || findInCol || findInDiagonalUp || findInDiagonalDown)
			game.setFinishedGame(true);

		if (game.isFinishedGame()) {
			// Get the type of the coin
			PlaceInGrid placeInGrid = result.get(0);
			Coin coin = game.getGame()[placeInGrid.getRow()][placeInGrid.getCol()];
			
			// See which player has the same type of the coin
			if (game.getPlayer1().getCoin().equals(coin))
				winner = game.getPlayer1();
			else
				winner = game.getPlayer2();
			
		} else {
			// If the game not finished yet, change the turn to the other player
			
			if (game.getPlayerTurn().getId().equals(game.getPlayer1().getId())) {
				game.setPlayerTurn(game.getPlayer2());
			} else {
				game.setPlayerTurn(game.getPlayer1());
			}
		}

		// If the game is full of coins, and don't have more option, finishe the game and it's a draw
		if (game.getTotalCoinsInGrid() >= GAME_ROW * GAME_COL) {
			game.setFinishedGame(true);
			winner = null;
		}
	}

	/**
	 * This method finds in diagonal down if they have 4 connects. Find one and verify if the next 3 are the same.
	 * 
	 *   |   |   |   |   |   |   | 
	 *   |   |   |   |   |   |   | 
	 *   |   |   | X |   |   |   | 
	 *   |   |   | x | X |   |   | 
	 *   |   |   | o | x | X |   | 
	 *   |   |   | x | o | o | X |
	 * 
	 * @return true and and on the list the results
	 */
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

	/**
	 * This method finds in diagonal up if they have 4 connects. Find one and verify if the next 3 are the same.
	 * 
	 *   |   |   |   |   |   |   | 
	 *   |   |   |   |   |   |   | 
	 *   |   |   | X |   |   |   | 
	 *   |   | X | o |   |   |   | 
	 *   | X | o | o |   |   |   | 
	 * X | o | o | x |   |   |   |
	 * 
	 * @return true and and on the list the results
	 */
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

	/**
	 * This method finds in column if they have 4 connects. Count if they are 4 in the sequel, if is not start to count again.
	 * 
	 *   |   |   |   |   |   |   | 
	 *   |   |   |   |   |   |   | 
	 *   |   |   | X |   |   |   | 
	 *   |   | o | X |   |   |   | 
	 *   | o | o | X |   |   |   | 
	 * X | o | o | X |   |   |   |
	 * 
	 * @return true and and on the list the results
	 */
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

	/**
	 * This method finds in row if they have 4 connects. Count if they are 4 in the sequel, if is not start to count again.
	 * 
	 *   |   |   |   |   |   |   | 
	 *   |   |   |   |   |   |   | 
	 *   |   |   | o |   |   |   | 
	 *   |   | o | o |   |   |   | 
	 * X | X | X | X |   |   |   | 
	 * o | o | o | x |   |   |   |
	 * 
	 * @return true and and on the list the results
	 */
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
