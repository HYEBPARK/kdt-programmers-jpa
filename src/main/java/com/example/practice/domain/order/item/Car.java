package com.example.practice.domain.order.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CAR")
public class Car extends Item {
	private int power;

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
}
