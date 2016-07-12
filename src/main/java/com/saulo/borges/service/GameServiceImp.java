package com.saulo.borges.service;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saulo.borges.controller.form.GameCreateForm;
import com.saulo.borges.controller.form.MoveForm;
import com.saulo.borges.exception.AppException;
import com.saulo.borges.exception.InvalidCollunmExcepetion;
import com.saulo.borges.exception.PlayerNullExcpetion;
import com.saulo.borges.exception.SameCoinTypeExcpetion;
import com.saulo.borges.game.Connect4;
import com.saulo.borges.model.GameModel;
import com.saulo.borges.model.PlayerModel;
import com.saulo.borges.repository.GameRepository;

@Service
public class GameServiceImp implements GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private PlayerService playerService;

	public void saveGame(GameModel model) {
		gameRepository.save(model);
	}

	public GameModel findGameById(Integer gameId) throws AppException {
		GameModel game = gameRepository.findOne(gameId);
		
		if (game == null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "This game not exists");
		}
		return game;
	}

	public void dropACoin(MoveForm form, GameModel game, Connect4 connect4) throws AppException {
		if (game.getPlayer1().getId().intValue() != form.getPlayerId().intValue() || game.getPlayer2().getId().intValue() != form.getPlayerId().intValue()) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400,
					"This player is not playing this game");
//			throw new PlayerNotAllowedToPlay();
		}

		if (game.getPlayerTurn().getId().intValue() != form.getPlayerId().intValue()) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "It is not your turn to play");
		}

		PlayerModel player = game.getPlayerTurn();
		int collumn = form.getCol();
		try {
			connect4.dropCoin(collumn, player);
		} catch (InvalidCollunmExcepetion e) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, e.getMessage());
		}

		gameRepository.save(game);
	}
	
	public GameModel createAGame(GameCreateForm form, Connect4 connect4) throws AppException {
		PlayerModel player1 = playerService.findById(form.getPlayer1());
		PlayerModel player2 = playerService.findById(form.getPlayer2());

		try {
			connect4.newGame(player1, player2);
		} catch (SameCoinTypeExcpetion e) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400,
					"The two players have the same Coin. Please change one");
		} catch (PlayerNullExcpetion e) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400,
					"The player is not created yet. Create one before start the game");
		}

		GameModel game = connect4.getGame();
		gameRepository.save(game);
		return game;
	}

}
