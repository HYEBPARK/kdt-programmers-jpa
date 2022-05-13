package com.example.practice.order.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.example.practice.domain.order.OrderStatus;

import lombok.Builder;

@Builder
public class OrderDto {
	private String uuid;
	private LocalDateTime orderDatetime;
	private OrderStatus orderStatus;
	private String memo;

	private MemberDto memberDto;
	private List<OrderItemDto> orderItemDtos;

	public String getUuid() {
		return uuid;
	}

	public LocalDateTime getOrderDatetime() {
		return orderDatetime;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public String getMemo() {
		return memo;
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public List<OrderItemDto> getOrderItemDtos() {
		return orderItemDtos;
	}

	@Override
	public String toString() {
		return "OrderDto{" +
			"uuid='" + uuid + '\'' +
			", orderDatetime=" + orderDatetime +
			", orderStatus=" + orderStatus +
			", memo='" + memo + '\'' +
			", memberDto=" + memberDto.toString() +
			", orderItemDtos=" + orderItemDtos.get(0).toString()+
			'}';
	}
}
