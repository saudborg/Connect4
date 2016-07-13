package com.saulo.borges.game;

import java.util.Random;

import com.saulo.borges.exception.InvalidColumnExcepetion;
import com.saulo.borges.model.GameModel;
import com.saulo.borges.model.PlayerModel;

public class Connect4Utils {
	
	private Connect4 connect4;
	private GameModel game;
	
	public Connect4Utils(Connect4 connect4) {
		this.connect4 = connect4;
		this.game = connect4.getGame();
	}

	/**
	 * Print the current status of the game. Like this
	 *   |   |   |   |   |   |   | 0
	 *   |   |   |   |   |   |   | 1
	 *   |   | x | X |   |   |   | 2
	 *   |   | X | o |   |   |   | 3
	 * o | X | x | o |   |   | o | 4
	 * X | o | o | o | x |   | x | 5
	 * ___________________________
	 * 0 | 1 | 2 | 3 | 4 | 5 | 6 |
	 */
	public void printCurrentGame() {
		System.out.println();
		for (int i = 0; i < game.getGame().length; i++) {
			for (int y = 0; y < game.getGame()[0].length; y++) {

				boolean print = false;
				for (PlaceInGrid placeInGrid : connect4.result) {
					if (i == placeInGrid.row && y == placeInGrid.col) {
						System.out.print(game.getGame()[i][y].getValue().toUpperCase());
						print = true;
					}
				}
				if (!print)
					System.out.print(game.getGame()[i][y].getValue());

				System.out.print(" | ");

			}
			System.out.println(i);
		}
		System.out.println("___________________________");
		System.out.println("0 | 1 | 2 | 3 | 4 | 5 | 6 |");
	}

	/**
	 * Make a random play. Use the class Random to get a number of one column
	 * @param player
	 * @return
	 * @throws InvalidColumnExcepetion
	 */
	public boolean randomPlay(PlayerModel player) throws InvalidColumnExcepetion {
		Random r = new Random();

		int col = 0;
		boolean dropCoin = false;
		while (!dropCoin) {
			col = r.nextInt(7);
			try {
			dropCoin = connect4.dropCoin(col, player);
			} catch (InvalidColumnExcepetion e){
				dropCoin = false;
			}
		}
		System.out.println("game.dropCoin(" + col + ", " + player.getName() + ");");

		return dropCoin;

	}

}
