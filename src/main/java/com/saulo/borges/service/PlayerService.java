package com.saulo.borges.service;

import com.saulo.borges.exception.AppException;
import com.saulo.borges.model.PlayerModel;

public interface PlayerService {

	public void save(PlayerModel model);

	public PlayerModel findById(Integer playerId) throws AppException;

}
