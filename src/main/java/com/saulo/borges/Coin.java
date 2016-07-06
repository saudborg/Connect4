package com.saulo.borges;

public enum Coin {
	
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

}
