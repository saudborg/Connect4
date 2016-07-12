package com.saulo.borges.controller.form;

import java.io.Serializable;

/**
 * Represents a form to create a request to create a new player
 * @author sauloborges
 *
 */
public class PlayerForm implements Serializable{

	private static final long serialVersionUID = -3160164856785064189L;
	
	private String name;
	
	private String color;

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

	public PlayerForm(String name, String color) {
		super();
		this.name = name;
		this.color = color;
	}
	
	public PlayerForm() {
	}
	
	

}
