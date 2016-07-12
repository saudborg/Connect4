package com.saulo.borges.service;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saulo.borges.exception.AppException;
import com.saulo.borges.model.PlayerModel;
import com.saulo.borges.repository.PlayerRepository;

@Service
public class PlayerServiceImp implements PlayerService {
	
	@Autowired
	private PlayerRepository repository;
	
	public void save(PlayerModel model){
		repository.save(model);
	}

	@Override
	public PlayerModel findById(Integer playerId) throws AppException {
		PlayerModel playerModel = repository.findOne(playerId);
		
		if (playerModel == null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "This player not exists");
		}
		return playerModel;
	}

}
