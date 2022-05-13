package com.example.practice.domain.order.item;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDto {
	private Long id;
	private int price;
	private int stockQuantity;

	private ItemType type;

	// Food
	private String chef;
	// Car
	private Integer power;
	// Furniture
	private Integer width;
	private Integer height;

	public Long getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public ItemType getType() {
		return type;
	}

	public String getChef() {
		return chef;
	}

	public Integer getPower() {
		return power;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public void setChef(String chef) {
		this.chef = chef;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
