package com.example.practice.domain.order.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FOOD")
public class Food extends Item {
	private String chef;

	public String getChef() {
		return chef;
	}

	public void setChef(String chef) {
		this.chef = chef;
	}
}
