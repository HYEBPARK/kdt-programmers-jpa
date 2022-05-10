package com.example.practice.domain.order;

import javax.persistence.Entity;

@Entity
public class Food extends Item {
	private String chef;

	public String getChef() {
		return chef;
	}

	public void setChef(String chef) {
		this.chef = chef;
	}
}
