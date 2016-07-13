package com.saulo.borges;

import java.util.Scanner;

import com.saulo.borges.exception.InvalidColumnExcepetion;
import com.saulo.borges.exception.PlayerNullExcpetion;
import com.saulo.borges.exception.SameCoinTypeExcpetion;
import com.saulo.borges.game.Coin;
import com.saulo.borges.game.Connect4;
import com.saulo.borges.game.Connect4Utils;
import com.saulo.borges.game.GameSettings;
import com.saulo.borges.model.PlayerModel;

/**
 * This class used just for tests.
 * 
 * You can run and play at console or create a list of random games to see the game working.
 * @author sauloborges
 *
 */
public class Main {
	private static Scanner sc;

	public static void main(String[] args) throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion {
		// consolePlay();
		randomPlay();
	}

	private static void consolePlay() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion {
		sc = new Scanner(System.in);

		PlayerModel player1 = new PlayerModel("Player 1", Coin.BLUE);
		player1.setId(1);
		PlayerModel player2 = new PlayerModel("Player 2", Coin.RED);
		player2.setId(2);

		Connect4 game = new Connect4(GameSettings.GAME_ROW, GameSettings.GAME_COL);
		game.newGame(player1, player2);
		Connect4Utils utils = new Connect4Utils(game);

		boolean player1Turn = true;

		while (!game.getGame().isFinishedGame()) {

			if (player1Turn) {
				System.out.println(player1.getCoin().getValue() + " Player 1 Turns: Choose the collunm");
				utils.printCurrentGame();
				Integer play = sc.nextInt();
				boolean dropCoin = game.dropCoin(play, player1);
				if (!dropCoin) {
					System.out.println("This colluns is full, choose another one");
				} else {
					game.hasAWinner();
					player1Turn = false;
				}
			}

			else if (!player1Turn) {
				System.out.println(player2.getCoin().getValue() + " Player 2 Turns: Choose the collunm");
				utils.printCurrentGame();
				Integer play = sc.nextInt();
				boolean dropCoin = game.dropCoin(play, player2);
				if (!dropCoin) {
					System.out.println("This colluns is full, choose another one");
				} else {
					game.hasAWinner();
					player1Turn = true;
				}
			}
		}

		utils.printCurrentGame();
		if (player1Turn) {
			System.out.println("Player 2 has won");
		} else {
			System.out.println("Player 1 has won");
		}
	}

	/**
	 * This method was created to help me in create unit tests
	 * @throws SameCoinTypeExcpetion
	 * @throws PlayerNullExcpetion
	 * @throws InvalidColumnExcepetion
	 */
	private static void randomPlay() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion {

		PlayerModel player1 = new PlayerModel("player1", Coin.BLUE);
		player1.setId(1);
		PlayerModel player2 = new PlayerModel("player2", Coin.RED);
		player2.setId(2);

		int i = 0;

		while (i < 50) {
			System.out.println("-------------------------");

			Connect4 game = new Connect4(GameSettings.GAME_ROW, GameSettings.GAME_COL);
			game.newGame(player1, player2);
			
			Connect4Utils utils = new Connect4Utils(game);

			boolean player1Turn = true;

			while (!game.getGame().isFinishedGame()) {

				if (player1Turn) {
					utils.randomPlay(player1);
					player1Turn = false;
				}

				else if (!player1Turn) {
					utils.randomPlay(player2);
					player1Turn = true;
				}
			}

			i++;

			utils.printCurrentGame();
			if (player1Turn) {
				System.out.println("Player 2 has won");
			} else {
				System.out.println("Player 1 has won");
			}
		}
	}
}
