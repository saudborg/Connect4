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

@RestController
public class PlayerController {

	@Autowired
	private PlayerService playerService;

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
	
//	@RequestMapping(value = "/player/{name}", method = RequestMethod.GET, produces = {
//			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
//	public String findByName(@PathVariable("name") String name) throws AppException {
//
//		FacebookUserEntity entity = facebookService.findById(userId);
//		FacebookUserJson json = new FacebookUserJson(entity);
//
//		return gson.toJson(json);
//	}

}
