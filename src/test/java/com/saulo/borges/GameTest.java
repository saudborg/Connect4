package com.saulo.borges;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.saulo.borges.exception.InvalidColumnExcepetion;
import com.saulo.borges.exception.PlayerNullExcpetion;
import com.saulo.borges.exception.SameCoinTypeExcpetion;
import com.saulo.borges.game.Coin;
import com.saulo.borges.game.Connect4;
import com.saulo.borges.model.PlayerModel;

public class GameTest {
	
	private PlayerModel player1;
	private PlayerModel player2;
	
	@Before
	public void initialize(){
		player1 = new PlayerModel("player1", Coin.BLUE);
		player1.setId(1);
		player2 = new PlayerModel("player2", Coin.RED);
		player2.setId(2);
	}
	
	@Test
	public void gameWonByDiagonalUp() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		// Winner will be player 2 by find In Diagonal Up
		game.dropCoin(0, player1);
		game.dropCoin(4, player2);
		game.dropCoin(2, player1);
		game.dropCoin(3, player2);
		game.dropCoin(0, player1);
		game.dropCoin(1, player2);
		game.dropCoin(4, player1);
		game.dropCoin(3, player2);
		game.dropCoin(6, player1);
		game.dropCoin(4, player2);
		game.dropCoin(5, player1);
		game.dropCoin(3, player2);
		game.dropCoin(2, player1);
		game.dropCoin(4, player2);
		game.dropCoin(4, player1);
		game.dropCoin(6, player2);
		game.dropCoin(5, player1);
		game.dropCoin(1, player2);
		game.dropCoin(2, player1);
		game.dropCoin(4, player2);
		game.dropCoin(6, player1);
		game.dropCoin(5, player2);
		game.dropCoin(0, player1);
		game.dropCoin(5, player2);
		game.dropCoin(6, player1);
		game.dropCoin(0, player2);
		game.dropCoin(0, player1);
		game.dropCoin(6, player2);
		
		
		assertEquals(true, game.getGame().isFinishedGame());
		assertEquals(player2, game.winner);
		
		assertEquals("[4,3]", game.result.get(0).toString());
		assertEquals("[3,4]", game.result.get(1).toString());
		assertEquals("[2,5]", game.result.get(2).toString());
		assertEquals("[1,6]", game.result.get(3).toString());
	}
	
	@Test
	public void gameWonColumn() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		// Winner will be player 2 by find In Column
		game.dropCoin(4, player1);
		game.dropCoin(3, player2);
		game.dropCoin(5, player1);
		game.dropCoin(1, player2);
		game.dropCoin(2, player1);
		game.dropCoin(6, player2);
		game.dropCoin(5, player1);
		game.dropCoin(1, player2);
		game.dropCoin(2, player1);
		game.dropCoin(1, player2);
		game.dropCoin(5, player1);
		game.dropCoin(1, player2);
		
		assertEquals(true, game.getGame().isFinishedGame());
		assertEquals(player2, game.winner);
		
		assertEquals("[2,1]", game.result.get(0).toString());
		assertEquals("[3,1]", game.result.get(1).toString());
		assertEquals("[4,1]", game.result.get(2).toString());
		assertEquals("[5,1]", game.result.get(3).toString());
		
	}
	
	@Test
	public void gameWithoutWinnerYet() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		// Not winner yet
		game.dropCoin(1, player1);
		game.dropCoin(2, player2);
		game.dropCoin(1, player1);
		game.dropCoin(3, player2);
		game.dropCoin(3, player1);
		game.dropCoin(6, player2);
		game.dropCoin(2, player1);
		game.dropCoin(6, player2);
		game.dropCoin(0, player1);
		game.dropCoin(5, player2);
		
		assertEquals(false, game.getGame().isFinishedGame());
		assertEquals(null, game.winner);
	}
	
	@Test
	public void gameWonByDiagonalDown() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		// Winner will be player 1 by find In Diagonal Down
		game.dropCoin(4, player1);
		game.dropCoin(5, player2);
		game.dropCoin(3, player1);
		game.dropCoin(2, player2);
		game.dropCoin(0, player1);
		game.dropCoin(0, player2);
		game.dropCoin(5, player1);
		game.dropCoin(1, player2);
		game.dropCoin(0, player1);
		game.dropCoin(4, player2);
		game.dropCoin(4, player1);
		game.dropCoin(4, player2);
		game.dropCoin(2, player1);
		game.dropCoin(1, player2);
		game.dropCoin(0, player1);
		game.dropCoin(0, player2);
		game.dropCoin(3, player1);
		game.dropCoin(2, player2);
		game.dropCoin(1, player1);
		
		assertEquals(true, game.getGame().isFinishedGame());
		assertEquals(player1, game.winner);
		
		assertEquals("[2,0]", game.result.get(0).toString());
		assertEquals("[3,1]", game.result.get(1).toString());
		assertEquals("[4,2]", game.result.get(2).toString());
		assertEquals("[5,3]", game.result.get(3).toString());
		
	}
	
	@Test
	public void gameWonByRow() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		// Winner will be player 1 by find In row
		game.dropCoin(6, player1);
		game.dropCoin(1, player2);
		game.dropCoin(4, player1);
		game.dropCoin(4, player2);
		game.dropCoin(3, player1);
		game.dropCoin(6, player2);
		game.dropCoin(3, player1);
		game.dropCoin(1, player2);
		game.dropCoin(5, player1);
		
		assertEquals(true, game.getGame().isFinishedGame());
		assertEquals(player1, game.winner);
		
		assertEquals("[5,3]", game.result.get(0).toString());
		assertEquals("[5,4]", game.result.get(1).toString());
		assertEquals("[5,5]", game.result.get(2).toString());
		assertEquals("[5,6]", game.result.get(3).toString());
		
	}
	
	/**
	 * Covers the fact that one last piece in the first row of the same color of the first in the second row
	 * @throws SameCoinTypeExcpetion
	 */
	@Test
	public void gameWithCountInDifferentsRows() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		game.dropCoin(5, player1);
		game.dropCoin(2, player2);
		game.dropCoin(0, player1);
		game.dropCoin(2, player2);
		game.dropCoin(1, player1);
		game.dropCoin(4, player2);
		game.dropCoin(5, player1);
		game.dropCoin(2, player2);
		game.dropCoin(5, player1);
		game.dropCoin(4, player2);
		game.dropCoin(3, player1);
		game.dropCoin(0, player2);
		game.dropCoin(0, player1);
		game.dropCoin(4, player2);
		game.dropCoin(0, player1);
		game.dropCoin(0, player2);
		game.dropCoin(0, player1);
		game.dropCoin(3, player2);
		game.dropCoin(6, player1);
		game.dropCoin(1, player2);
		
		assertEquals(true, game.getGame().isFinishedGame());
		assertEquals(player2, game.winner);
		
		assertEquals("[4,0]", game.result.get(0).toString());
		assertEquals("[4,1]", game.result.get(1).toString());
		assertEquals("[4,2]", game.result.get(2).toString());
		assertEquals("[4,3]", game.result.get(3).toString());
		
	}
	
	/**
	 * Draw game
	 * @throws SameCoinTypeExcpetion
	 */
	@Test
	public void gameDraw() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		game.dropCoin(0, player1);
		game.dropCoin(1, player2);
		game.dropCoin(3, player1);
		game.dropCoin(1, player2);
		game.dropCoin(0, player1);
		game.dropCoin(0, player2);
		game.dropCoin(0, player1);
		game.dropCoin(0, player2);
		game.dropCoin(5, player1);
		game.dropCoin(1, player2);
		game.dropCoin(1, player1);
		game.dropCoin(4, player2);
		game.dropCoin(0, player1);
		game.dropCoin(3, player2);
		game.dropCoin(6, player1);
		game.dropCoin(6, player2);
		game.dropCoin(1, player1);
		game.dropCoin(3, player2);
		game.dropCoin(6, player1);
		game.dropCoin(6, player2);
		game.dropCoin(5, player1);
		game.dropCoin(1, player2);
		game.dropCoin(5, player1);
		game.dropCoin(3, player2);
		game.dropCoin(3, player1);
		game.dropCoin(3, player2);
		game.dropCoin(4, player1);
		game.dropCoin(2, player2);
		game.dropCoin(4, player1);
		game.dropCoin(5, player2);
		game.dropCoin(4, player1);
		game.dropCoin(4, player2);
		game.dropCoin(5, player1);
		game.dropCoin(6, player2);
		game.dropCoin(5, player1);
		game.dropCoin(2, player2);
		game.dropCoin(2, player1);
		game.dropCoin(6, player2);
		game.dropCoin(4, player1);
		game.dropCoin(2, player2);
		game.dropCoin(2, player1);
		game.dropCoin(2, player2);
		
		assertEquals(true, game.getGame().isFinishedGame());
		assertEquals(null, game.winner);
		
	}
	
	/**
	 * Test for the case to be filled with a column which has two straight at the top of the column
	 * @throws SameCoinTypeExcpetion
	 */
	@Test
	public void gameCoverTopOfTheColumn() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		game.dropCoin(0, player1);
		game.dropCoin(0, player2);
		game.dropCoin(0, player1);
		game.dropCoin(0, player2);
		game.dropCoin(5, player1);
		game.dropCoin(3, player2);
		game.dropCoin(4, player1);
		game.dropCoin(6, player2);
		game.dropCoin(5, player1);
		game.dropCoin(4, player2);
		game.dropCoin(0, player1);
		game.dropCoin(1, player2);
		game.dropCoin(0, player1);
		game.dropCoin(2, player2);
		game.dropCoin(3, player1);
		game.dropCoin(6, player2);
		game.dropCoin(6, player1);
		game.dropCoin(1, player2);
		game.dropCoin(3, player1);
		game.dropCoin(2, player2);
		game.dropCoin(3, player1);
		game.dropCoin(1, player2);
		
		assertEquals(true, game.getGame().isFinishedGame());
		assertEquals(player2, game.winner);
		
		assertEquals("[2,0]", game.result.get(0).toString());
		assertEquals("[3,1]", game.result.get(1).toString());
		assertEquals("[4,2]", game.result.get(2).toString());
		assertEquals("[5,3]", game.result.get(3).toString());
		
	}
	
	/**
	 * Winning in two ways
	 * @throws SameCoinTypeExcpetion
	 */
	@Test
	public void gameWonInTwoWays() throws SameCoinTypeExcpetion, PlayerNullExcpetion, InvalidColumnExcepetion{
		Connect4 game = new Connect4();
		game.newGame(player1, player2);
		
		game.dropCoin(6, player1);
		game.dropCoin(5, player2);
		game.dropCoin(0, player1);
		game.dropCoin(0, player2);
		game.dropCoin(4, player1);
		game.dropCoin(1, player2);
		game.dropCoin(3, player1);
		game.dropCoin(2, player2);
		game.dropCoin(5, player1);
		game.dropCoin(5, player2);
		game.dropCoin(2, player1);
		game.dropCoin(2, player2);
		game.dropCoin(6, player1);
		game.dropCoin(2, player2);
		game.dropCoin(1, player1);
		game.dropCoin(2, player2);
		game.dropCoin(2, player1);
		game.dropCoin(4, player2);
		game.dropCoin(5, player1);
		game.dropCoin(4, player2);
		game.dropCoin(3, player1);
		game.dropCoin(1, player2);
		game.dropCoin(5, player1);
		game.dropCoin(1, player2);
		game.dropCoin(1, player1);
		game.dropCoin(3, player2);
		
		assertEquals(true, game.getGame().isFinishedGame());
		assertEquals(player2, game.winner);
		
		// row
		assertEquals("[3,1]", game.result.get(0).toString());
		assertEquals("[3,2]", game.result.get(1).toString());
		assertEquals("[3,3]", game.result.get(2).toString());
		assertEquals("[3,4]", game.result.get(3).toString());
		
		// diagonal down
		assertEquals("[2,2]", game.result.get(4).toString());
		assertEquals("[3,3]", game.result.get(5).toString());
		assertEquals("[4,4]", game.result.get(6).toString());
		assertEquals("[5,5]", game.result.get(7).toString());
		
	}

}
