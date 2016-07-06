package com.saulo.borges;

import java.util.Scanner;

public class Main {
	private static Scanner sc;

	public static void main(String[] args) throws SameCoinTypeExcpetion {
		// consolePlay();
		randomPlay();
	}

	private static void consolePlay() throws SameCoinTypeExcpetion {
		sc = new Scanner(System.in);

		// Criar jogadores
		Player player1 = new Player("Player 1", Coin.BLUE);
		Player player2 = new Player("Player 2", Coin.RED);

		// Começar partida com jogadores
		Game game = new Game();
		game.connect4(player1, player2);

		boolean player1Turn = true;

		while (!game.finishedGame) {

			if (player1Turn) {
				System.out.println(player1.getCoin().getValue() + " Player 1 Turns: Choose the collunm");
				game.printCurrentGame();
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
				game.printCurrentGame();
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

		game.printCurrentGame();
		if (player1Turn) {
			System.out.println("Player 2 has won");
		} else {
			System.out.println("Player 1 has won");
		}
	}

	private static void randomPlay() throws SameCoinTypeExcpetion {

		// Criar jogadores
		Player player1 = new Player("player1", Coin.BLUE);
		Player player2 = new Player("player2", Coin.RED);

		// Começar partida com jogadores

		String way = " ";
		
		int i = 0;

		while (i < 50) {
			System.out.println("-------------------------");

			Game game = new Game();
			game.connect4(player1, player2);

			boolean player1Turn = true;

			while (!game.finishedGame) {

				if (player1Turn) {
					game.randomPlay(player1);
					player1Turn = false;
				}

				else if (!player1Turn) {
					game.randomPlay(player2);
					player1Turn = true;
				}
			}

			way = game.way;
			i++;

//			if (way.equals("as")) {
				game.printCurrentGame();
				if (player1Turn) {
					System.out.println("Player 2 has won");
				} else {
					System.out.println("Player 1 has won");
				}
//			}
		}
	}
}
