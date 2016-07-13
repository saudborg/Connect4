package com.saulo.borges.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saulo.borges.controller.form.MoveForm;
import com.saulo.borges.controller.json.PlayGameJson;
import com.saulo.borges.controller.json.ResultGameJson;
import com.saulo.borges.exception.AppException;
import com.saulo.borges.game.Connect4;
import com.saulo.borges.model.GameModel;
import com.saulo.borges.model.PlayerModel;
import com.saulo.borges.service.GameService;

/**
 * This controller represents a Controller to access the game online
 * @author sauloborges
 *
 */
@Controller
public class Connect4Controller {

	@Autowired
	private GameService gameService;


	/**
	 * Redirect to a game in URL
	 * example: http://localhost:8080/game/1/online?player=1
	 * @param gameIdStr = The game created before
	 * @param playerId = Id of your player
	 * @param model
	 * @return
	 * @throws AppException
	 */
	@RequestMapping("/game/{game_id}/online")
	public String startAGame(@PathVariable("game_id") String gameIdStr, @RequestParam("player") String playerId,
			Model model) throws AppException {

		int gameId = Integer.parseInt(gameIdStr);

		GameModel game = gameService.findGameById(gameId);
		
		Connect4 connect4 = new Connect4(game);
		connect4.hasAWinner();

		model.addAttribute("game", game);
		model.addAttribute("playerId", playerId);

		return "game";
	}

	/**
	 * Method responsible to receive a new play, put the coin in the game and return the status of the game
	 * @param message
	 * @return
	 */
	@MessageMapping("/connect4")
	@SendTo("/topic/entries")
	public ResultGameJson play(PlayGameJson message) {
		ResultGameJson json = new ResultGameJson();

		try {
			int gameId = Integer.parseInt(message.getGame());
			GameModel game = gameService.findGameById(gameId);

			Connect4 connect4 = new Connect4(game);

			int col = Integer.parseInt(message.getCol());
			Integer playerId = Integer.parseInt(message.getPlayer());
			MoveForm form = new MoveForm(playerId, col);
			
			gameService.dropACoin(form, game, connect4);
			json = new ResultGameJson(game, connect4);
			if(game.isFinishedGame()){
				PlayerModel winner = connect4.winner;
				if(winner != null){
					json.setContent("Player " + winner.getName() + " has won");
					return json;
				}
			}
			json.setContent("[Player " + message.getPlayer() + "] played in " + message.getCol());
		} catch (AppException e) {
			json.setContent("[Player " + message.getPlayer() + "] msg = " + e.getMessage());
		} 

		return json;
	}
}
