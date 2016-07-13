package com.saulo.borges.controller;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.saulo.borges.controller.form.PlayerForm;
import com.saulo.borges.exception.AppException;
import com.saulo.borges.game.Coin;
import com.saulo.borges.model.PlayerModel;
import com.saulo.borges.service.PlayerService;

/**
 * This is a RestController which is responsible to manege a player
 * @author sauloborges
 *
 */
@RestController
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	/**
	 * This method is responsible to create a new player
	 * Expects in the parameters, the name and also the color of the coin. Could be Blue or Red
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/newPlayer", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public Response newPlayer(@ModelAttribute PlayerForm form) throws AppException {

		Coin coin = Coin.findByColor(form.getColor());
		if (coin.equals(Coin.SPACE)) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400,
					"This color doesn't exists. Try Blue or Red");
		}
		PlayerModel model = new PlayerModel(form.getName(), coin);

		playerService.save(model);
		return Response.status(Response.Status.CREATED)
				.entity("A new player has been inserted: id=[" + model.getId() + "]").build();
	}

	/**
	 * This method you can change the color of your player before the game starts.
	 * 
	 * It is necessary if two players are going to play and have the same type of coin
	 * @param playerId
	 * @param color
	 * @return
	 * @throws AppException
	 */
	@RequestMapping(value = "/player/{player_id}/changeColor", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public Response changeColor(@PathVariable("player_id") Integer playerId,  String color)
			throws AppException {

		PlayerModel playerModel = playerService.findById(playerId);

		Coin coin = Coin.findByColor(color);
		if (coin.equals(Coin.SPACE)) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400,
					"This color doesn't exists. Try BLUE or RED");
		}

		playerModel.setCoin(coin);

		playerService.save(playerModel);
		return Response.status(Response.Status.CREATED)
				.entity("A new color has been updated: [id: " + playerModel.getId() + ", color: " + color + "]")
				.build();
	}
	
}
