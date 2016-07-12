package com.saulo.borges.model;

public class GameMessageModel {
	
	private String name;
	
	private String color;
	
	private String colunm;
	
	private Integer game;
	
	public GameMessageModel(String name, String color, String colunm, Integer game) {
		super();
		this.name = name;
		this.color = color;
		this.colunm = colunm;
		this.setGame(game);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColunm() {
		return colunm;
	}

	public void setColunm(String colunm) {
		this.colunm = colunm;
	}

	public Integer getGame() {
		return game;
	}

	public void setGame(Integer game) {
		this.game = game;
	}
	
	

}
