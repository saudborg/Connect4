package com.saulo.borges;

import org.junit.Before;
import org.junit.Test;

import com.saulo.borges.exception.InvalidCollunmExcepetion;
import com.saulo.borges.exception.PlayerNullExcpetion;
import com.saulo.borges.exception.SameCoinTypeExcpetion;
import com.saulo.borges.game.Coin;
import com.saulo.borges.game.Connect4;
import com.saulo.borges.model.PlayerModel;

public class GameExcpetionTest {
	
	private PlayerModel player1;
	private PlayerModel player2;
	
	@Before
	public void initialize(){
		player1 = new PlayerModel("player1", Coin.BLUE);
		player1.setId(1);
		player2 = new PlayerModel("player2", Coin.RED);
		player2.setId(2);
	}
	
	@Test(expected = SameCoinTypeExcpetion.class)
	public void gameSameCoinType() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidCollunmExcepetion{
		player2 = new PlayerModel("player2", Coin.BLUE);
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
	}
	
	@Test(expected = PlayerNullExcpetion.class)
	public void gamePlayerNull() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidCollunmExcepetion{
		player2 = null;
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
	}
	
	@Test(expected = InvalidCollunmExcepetion.class)
	public void gameInvalidCol() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidCollunmExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		game.dropCoin(0, player1);
		game.dropCoin(7, player2);
	}

}
