package com.example.practice.domain.order;

import javax.persistence.Entity;

@Entity
public class Furniture extends Item {
	private int width;
	private int height;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
