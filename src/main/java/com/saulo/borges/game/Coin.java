package com.saulo.borges.game;

//@Entity 
public enum Coin  {
	
	BLUE("Blue", "x"),
	
	RED("Red" , "o"),
	
	SPACE("Empty", " ");
	
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

	private String color;
	private String value;
	
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
