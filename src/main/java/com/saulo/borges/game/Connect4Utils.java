package com.saulo.borges.game;

import java.util.Random;

import com.saulo.borges.exception.InvalidCollunmExcepetion;
import com.saulo.borges.model.GameModel;
import com.saulo.borges.model.PlayerModel;

public class Connect4Utils {
	
	private Connect4 connect4;
	private GameModel game;
	
	public Connect4Utils(Connect4 connect4) {
		this.connect4 = connect4;
		this.game = connect4.getGame();
	}

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

	public boolean randomPlay(PlayerModel player) throws InvalidCollunmExcepetion {
		Random r = new Random();

		int col = 0;
		boolean dropCoin = false;
		while (!dropCoin) {
			col = r.nextInt(7);
			dropCoin = connect4.dropCoin(col, player);
		}
		System.out.println("game.dropCoin(" + col + ", " + player.getName() + ");");

		return dropCoin;

	}

}
