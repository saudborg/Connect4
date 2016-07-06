package com.saulo.borges;

public class Player {
	
	private String name;
	
	private Coin coin;
	
	public Player(String name, Coin coin) {
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
	
	

}
