package com.example.practice.order.controller;

import com.example.practice.domain.order.item.ItemDto;

import lombok.Builder;

@Builder
public class OrderItemDto {

	private Long id;
	private Integer price;
	private Integer quantity;

	private OrderDto orderDto;
	private ItemDto itemDto;

	public Long getId() {
		return id;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public OrderDto getOrderDto() {
		return orderDto;
	}

	public ItemDto getItemDto() {
		return itemDto;
	}

	@Override
	public String toString() {
		return "OrderItemDto{" +
			"id=" + id +
			", price=" + price +
			", quantity=" + quantity +
			", orderDto=" + orderDto +
			", itemDto=" + itemDto.toString() +
			'}';
	}
}
