package com.saulo.borges;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GameTest {
	
	private Player player1;
	private Player player2;
	
	@Before
	public void initialize(){
		player1 = new Player("player1", Coin.BLUE);
		player2 = new Player("player2", Coin.RED);
	}
	
	@Test
	public void game1() throws SameCoinTypeExcpetion{
		Game game = new Game();
		game.connect4(player1, player2);
		
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
		
		
		assertEquals(true, game.finishedGame);
		assertEquals(player2, game.winner);
		
		assertEquals("[4,3]", game.result.get(0).toString());
		assertEquals("[3,4]", game.result.get(1).toString());
		assertEquals("[2,5]", game.result.get(2).toString());
		assertEquals("[1,6]", game.result.get(3).toString());
	}
	
	@Test
	public void game2() throws SameCoinTypeExcpetion{
		Game game = new Game();
		game.connect4(player1, player2);
		
		// Winner will be player 2 by find In Diagonal Up
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
		
		assertEquals(true, game.finishedGame);
		assertEquals(player2, game.winner);
		
		assertEquals("[2,1]", game.result.get(0).toString());
		assertEquals("[3,1]", game.result.get(1).toString());
		assertEquals("[4,1]", game.result.get(2).toString());
		assertEquals("[5,1]", game.result.get(3).toString());
		
	}
	
	@Test
	public void game3() throws SameCoinTypeExcpetion{
		Game game = new Game();
		game.connect4(player1, player2);
		
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
		
		assertEquals(false, game.finishedGame);
		assertEquals(null, game.winner);
	}
	
	@Test
	public void game4() throws SameCoinTypeExcpetion{
		Game game = new Game();
		game.connect4(player1, player2);
		
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
		
		assertEquals(true, game.finishedGame);
		assertEquals(player1, game.winner);
		
		assertEquals("[2,0]", game.result.get(0).toString());
		assertEquals("[3,1]", game.result.get(1).toString());
		assertEquals("[4,2]", game.result.get(2).toString());
		assertEquals("[5,3]", game.result.get(3).toString());
		
	}
	
	@Test
	public void game5() throws SameCoinTypeExcpetion{
		Game game = new Game();
		game.connect4(player1, player2);
		
		// Winner will be player 1 by find In Diagonal Down
		game.dropCoin(6, player1);
		game.dropCoin(1, player2);
		game.dropCoin(4, player1);
		game.dropCoin(4, player2);
		game.dropCoin(3, player1);
		game.dropCoin(6, player2);
		game.dropCoin(3, player1);
		game.dropCoin(1, player2);
		game.dropCoin(5, player1);
		
		assertEquals(true, game.finishedGame);
		assertEquals(player1, game.winner);
		
		assertEquals("[5,3]", game.result.get(0).toString());
		assertEquals("[5,4]", game.result.get(1).toString());
		assertEquals("[5,5]", game.result.get(2).toString());
		assertEquals("[5,6]", game.result.get(3).toString());
		
	}
	
	/**
	 * Cobre o fato de ter uma ultima peca na primeira linha da mesma cor da primeira na segunda linha
	 * @throws SameCoinTypeExcpetion
	 */
	@Test
	public void game6() throws SameCoinTypeExcpetion{
		Game game = new Game();
		game.connect4(player1, player2);
		
		// Winner will be player 1 by find In Diagonal Down
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
		
		assertEquals(true, game.finishedGame);
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
	public void game7() throws SameCoinTypeExcpetion{
		Game game = new Game();
		game.connect4(player1, player2);
		
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
		
		assertEquals(true, game.finishedGame);
		assertEquals(null, game.winner);
		
	}
	
	/**
	 * Teste para o caso de estar preenchida uma coluna sendo que tem dois seguidos no topo da coluna
	 * @throws SameCoinTypeExcpetion
	 */
	@Test
	public void game8() throws SameCoinTypeExcpetion{
		Game game = new Game();
		game.connect4(player1, player2);
		
		// Winner will be player 1 by find In Diagonal Down
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
		
		assertEquals(true, game.finishedGame);
		assertEquals(player2, game.winner);
		
		assertEquals("[2,0]", game.result.get(0).toString());
		assertEquals("[3,1]", game.result.get(1).toString());
		assertEquals("[4,2]", game.result.get(2).toString());
		assertEquals("[5,3]", game.result.get(3).toString());
		
	}
	
	/**
	 * Ganhar em dois sentidos
	 * @throws SameCoinTypeExcpetion
	 */
	@Test
	public void game9() throws SameCoinTypeExcpetion{
		Game game = new Game();
		game.connect4(player1, player2);
		
		// Winner will be player 1 by find In Diagonal Down
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
		
		assertEquals(true, game.finishedGame);
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
	

	// fazer teste com exceçao
}
