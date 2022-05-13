package com.example.practice.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.practice.domain.order.OrderStatus;
import com.example.practice.domain.order.item.ItemDto;
import com.example.practice.domain.order.item.ItemType;
import com.example.practice.exception.NotFoundException;
import com.example.practice.order.controller.MemberDto;
import com.example.practice.order.controller.OrderDto;
import com.example.practice.order.controller.OrderItemDto;
import com.example.practice.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	String uuid = UUID.randomUUID().toString();

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

		// Then
		log.info("UUID:{}", uuid);
	}

	@AfterEach
	void tearDown() {
		orderRepository.deleteAll();
	}

	@Test
	void findAll() {
		Page<OrderDto> orders = orderService.findAll(PageRequest.of(0, 10));
		log.info("{}", orders);
	}

	@Test
	void findOne() throws NotFoundException {
		log.info("uuid:{}", uuid);
		OrderDto one = orderService.findOne(uuid);
		log.info("{}", one);
	}

}