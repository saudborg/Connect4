package com.saulo.borges.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.saulo.borges.game.Coin;

@Entity(name = "player")
public class PlayerModel implements Serializable {
	
	private static final long serialVersionUID = 8364682788872634369L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private Coin coin;
	
	public PlayerModel() {
	}
	
	public PlayerModel(String name, Coin coin) {
		this.name = name;
		this.coin = coin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
