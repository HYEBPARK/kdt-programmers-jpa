package com.example.practice.order.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.practice.domain.order.OrderStatus;
import com.example.practice.domain.order.item.ItemDto;
import com.example.practice.domain.order.item.ItemType;
import com.example.practice.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private OrderService orderService;
	@Autowired
	private ObjectMapper objectMapper;
	private String uuid = UUID.randomUUID().toString();

	@BeforeEach
	void setUp() {
		// Given
		OrderDto orderDto = OrderDto.builder()
		.uuid(uuid)
			.memo("문 앞에 두고 가주세요 ~")
			.orderDatetime(LocalDateTime.now())
		.orderStatus(OrderStatus.OPENED)
			.memberDto(
		MemberDto.builder()
					.name("hyebpark")
					.nickName("hyeb")
					.address("부산시 몰랑몰랑")
					.age(12)
					.description("---")
					.build()
			)
				.orderItemDtos(List.of(
		OrderItemDto.builder()
					.price(1000)
					.quantity(100)
					.itemDto(ItemDto.builder()
						.type(ItemType.FOOD)
						.chef("hyeb")
						.price(1000)
						.build())
		.build()
			))
				.build();
	// When
	String uuid = orderService.save(orderDto);
}

	@Test
	void savedCallTest() throws Exception {
		OrderDto orderDto = OrderDto.builder()
			.uuid(UUID.randomUUID().toString())
			.memo("문 앞에 두고 가주세요 ~")
			.orderDatetime(LocalDateTime.now())
			.orderStatus(OrderStatus.OPENED)
			.memberDto(
				MemberDto.builder()
					.name("hyebpark")
					.nickName("hyeb2")
					.address("부산시 몰랑몰랑")
					.age(12)
					.description("---")
					.build()
			)
			.orderItemDtos(List.of(
				OrderItemDto.builder()
					.price(1000)
					.quantity(100)
					.itemDto(ItemDto.builder()
						.type(ItemType.FOOD)
						.chef("hyeb")
						.price(1000)
						.build())
					.build()
			))
			.build();

		//when
		mockMvc.perform(post("/orders")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(orderDto)))
			.andExpect(status().isOk())
			.andDo(print());
	}
}