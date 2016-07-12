package com.saulo.borges.service;

import com.saulo.borges.controller.form.GameCreateForm;
import com.saulo.borges.controller.form.MoveForm;
import com.saulo.borges.exception.AppException;
import com.saulo.borges.game.Connect4;
import com.saulo.borges.model.GameModel;

public interface GameService {

	public void saveGame(GameModel model);

	public GameModel findGameById(Integer gameId) throws AppException;
	
	public void dropACoin(MoveForm form, GameModel game, Connect4 connect4) throws AppException;
	
	public GameModel createAGame(GameCreateForm form, Connect4 connect4) throws AppException;

}
