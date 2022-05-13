package com.example.practice.order.controller;

import java.util.List;

import com.example.practice.order.controller.OrderDto;

import lombok.Builder;

@Builder
public class MemberDto {
	private Long id;
	private String name;
	private String nickName;
	private int age;
	private String address;
	private String description;
	private List<OrderDto> orderDto;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNickName() {
		return nickName;
	}

	public int getAge() {
		return age;
	}

	public String getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
	}

	public List<OrderDto> getOrderDto() {
		return orderDto;
	}

	@Override
	public String toString() {
		return "MemberDto{" +
			"id=" + id +
			", name='" + name + '\'' +
			", nickName='" + nickName + '\'' +
			", age=" + age +
			", address='" + address + '\'' +
			", description='" + description + '\'' +
			", orderDto=" + orderDto +
			'}';
	}
}
