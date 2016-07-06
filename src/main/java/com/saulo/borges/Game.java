package com.saulo.borges;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";

	private static final Integer GAME_ROW = 6;
	private static final Integer GAME_COL = 7;

	private static Coin[][] GAME = new Coin[GAME_ROW][GAME_COL];

	public Player player1;
	public Player player2;

	public Player winner;

	public boolean finishedGame = false;

	public List<PlaceInGrid> result;

	// TODO retirar
	public String way;

	private int totalCoinsInGrid = 0;

	public void connect4(Player player1, Player player2) throws SameCoinTypeExcpetion {

		if (player1.getCoin().getValue().equals(player2.getCoin().getValue())) {
			throw new SameCoinTypeExcpetion();
		}

		this.player1 = player1;
		this.player2 = player2;

		result = new ArrayList<PlaceInGrid>();

		initializeGame();
	}

	/**
	 * Fill the game with spaces
	 */
	private void initializeGame() {
		totalCoinsInGrid = 0;
		for (int row = 0; row < GAME.length; row++) {
			for (int col = 0; col < GAME[0].length; col++) {
				GAME[row][col] = Coin.SPACE;
			}
		}
	}

	public boolean dropCoin(Integer play, Player player) {
		if (!GAME[0][play].equals(Coin.SPACE)) {
			return false;
		}

		for (int row = GAME.length - 1; row >= 0; row--) {
			if (GAME[row][play].equals(Coin.SPACE)) {
				GAME[row][play] = player.getCoin();

				totalCoinsInGrid++;
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
			this.finishedGame = true;

		if (this.finishedGame) {
			PlaceInGrid placeInGrid = result.get(0);
			Coin coin = GAME[placeInGrid.getRow()][placeInGrid.getCol()];
			if (player1.getCoin().equals(coin))
				winner = player1;
			else
				winner = player2;
		}

		if (totalCoinsInGrid >= GAME_ROW * GAME_COL) {
			this.finishedGame = true;
			winner = null;
		}
	}

	private boolean findInDiagonalDown() {
		for (int row = 0; row < GAME.length - 3; row++) {
			for (int col = 0; col < GAME[0].length - 3; col++) {
				if (!GAME[row][col].equals(Coin.SPACE)) {
					if (GAME[row][col].equals(GAME[row + 1][col + 1]) //
							&& GAME[row][col].equals(GAME[row + 2][col + 2])//
							&& GAME[row][col].equals(GAME[row + 3][col + 3])) {

						result.add(new PlaceInGrid(row, col));
						result.add(new PlaceInGrid(row + 1, col + 1));
						result.add(new PlaceInGrid(row + 2, col + 2));
						result.add(new PlaceInGrid(row + 3, col + 3));

						System.out.println("findInDiagonalDown");
						way = "findInDiagonalDown";
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean findInDiagonalUp() {
		for (int row = GAME.length - 1; row >= 3; row--) {
			for (int col = 0; col < GAME[0].length - 3; col++) {
				if (!GAME[row][col].equals(Coin.SPACE)) {
					if (GAME[row][col].equals(GAME[row - 1][col + 1]) //
							&& GAME[row][col].equals(GAME[row - 2][col + 2])
							&& GAME[row][col].equals(GAME[row - 3][col + 3])) {

						result.add(new PlaceInGrid(row, col));
						result.add(new PlaceInGrid(row - 1, col + 1));
						result.add(new PlaceInGrid(row - 2, col + 2));
						result.add(new PlaceInGrid(row - 3, col + 3));

						System.out.println("findInDiagonalUp");
						way = "findInDiagonalUp";
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean findInCol() {
		for (int col = 0; col <= GAME[0].length - 1; col++) {
			int count = 1;
			for (int row = GAME.length - 1; row > 0; row--) {
				if (!GAME[row][col].equals(Coin.SPACE)) {
					if (GAME[row][col].equals(GAME[row - 1][col])) {
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
						way = "findInCol";
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean findInRows() {
		for (int row = GAME.length - 1; row >= 0; row--) {
			int count = 1;
			for (int col = 0; col < GAME[0].length - 1; col++) {
				if (!GAME[row][col].equals(Coin.SPACE)) {
					if (GAME[row][col].equals(GAME[row][col + 1])) {
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
						way = "findInRows";
						return true;
					}
				}
			}
		}
		return false;
	}

	public void printCurrentGame() {
		System.out.println();
		for (int i = 0; i < GAME.length; i++) {
			for (int y = 0; y < GAME[0].length; y++) {

				boolean print = false;
				for (PlaceInGrid placeInGrid : result) {
					if (i == placeInGrid.row && y == placeInGrid.col) {
						System.out.print(GAME[i][y].getValue().toUpperCase());
						print = true;
					}
				}
				if (!print)
					System.out.print(GAME[i][y].getValue());
				
				System.out.print(" | ");

			}
			System.out.println(i);
		}
		System.out.println("___________________________");
		System.out.println("0 | 1 | 2 | 3 | 4 | 5 | 6 |");
	}

	public boolean randomPlay(Player player) {
		Random r = new Random();

		int col = 0;
		boolean dropCoin = false;
		while (!dropCoin) {
			col = r.nextInt(7);
			dropCoin = this.dropCoin(col, player);
		}
		System.out.println("game.dropCoin(" + col + ", " + player.getName() + ");");

		return dropCoin;

	}

}
