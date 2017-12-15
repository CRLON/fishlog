package com.fishlog.model;

import java.io.Serializable;

public class Fish implements Serializable {

	private static final long serialVersionUID = -6094173246874099169L;
	private String species;
	private int weight;
	private int length;

	public Fish() {
	}

	public Fish(String species, int weight, int length) {
		this.species = species;
		this.weight = weight;
		this.length = length;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}


}
