package com.saulo.borges.controller;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.saulo.borges.controller.form.GameCreateForm;
import com.saulo.borges.controller.form.MoveForm;
import com.saulo.borges.controller.json.ResultGameJson;
import com.saulo.borges.exception.AppException;
import com.saulo.borges.game.Connect4;
import com.saulo.borges.model.GameModel;
import com.saulo.borges.service.GameService;

@RestController
public class GameController {

	@Autowired
	private GameService gameService;

	private Gson gson = new Gson();

	@RequestMapping(value = "/createGame", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public Response newGame(@ModelAttribute GameCreateForm form) throws AppException {

		Connect4 connect4 = new Connect4();
		GameModel game = gameService.createAGame(form, connect4);

		return Response.status(Response.Status.CREATED).entity("A new game has been created: id=[" + game.getId() + "]")
				.build();
	}

	@RequestMapping(value = "/game/{game_id}/play", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public String playGame(@ModelAttribute MoveForm form, @PathVariable("game_id") String gameIdStr)
			throws AppException {

		int gameId = Integer.parseInt(gameIdStr);
		if (form.getCol() == null || form.getPlayerId() == null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400,
					"Make sure in your parameters [playerId and col]");
		}

		GameModel game = gameService.findGameById(gameId);
		Connect4 connect4 = new Connect4(game);

		if (game.isFinishedGame()) {
			connect4.hasAWinner();
			ResultGameJson json = new ResultGameJson(game, connect4);
			return gson.toJson(json);
		}

		gameService.dropACoin(form, game, connect4);
		ResultGameJson json = new ResultGameJson(game, connect4);

		return gson.toJson(json);
	}

	@RequestMapping(value = "/game/{game_id}/status", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String showGame(@PathVariable("game_id") String gameIdStr) throws AppException {
		int gameId = Integer.parseInt(gameIdStr);

		GameModel game = gameService.findGameById(gameId);

		Connect4 connect4 = new Connect4(game);
		connect4.hasAWinner();

		ResultGameJson json = new ResultGameJson(game, connect4);
		return gson.toJson(json);
	}

}
