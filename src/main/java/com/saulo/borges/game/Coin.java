package com.saulo.borges.game;

/**
 * Represents the coin that the player can put in the game.
 * 
 * The Enum space is when the place is empty
 * @author sauloborges
 *
 */
public enum Coin  {
	
	BLUE("Blue", "x"),
	
	RED("Red" , "o"),
	
	SPACE("Empty", " ");
	
	/**
	 * Name of the color
	 */
	private String color;
	
	/**
	 * Value to show in the game
	 */
	private String value;
	
	Coin(String color, String value){
		this.setColor(color);
		this.setValue(value);
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Coin findByColor(String color){
		Coin[] values = Coin.values();
		for (Coin coin : values) {
			if(coin.getColor().equals(color)){
				return coin;
			}
		}
		return SPACE;
	}

	public static Coin findByValue(String value) {
		Coin[] values = Coin.values();
		for (Coin coin : values) {
			if(coin.getValue().equals(value)){
				return coin;
			}
		}
		return SPACE;
	}
	
}
