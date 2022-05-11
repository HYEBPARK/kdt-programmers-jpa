package com.example.practice.domain.order;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class OrderRepositoryTest {

	@Autowired
	OrderRepository orderRepository;

	@Test
	void test() {
		String uuid = UUID.randomUUID().toString();
		Order order = new Order();
		order.setUuid(uuid);
		order.setOrderStatus(OrderStatus.OPENED);
		order.setMemo("메모메모");
		order.setCreatedBy("hyebpark");
		order.setCreatedAt(LocalDateTime.now());

		orderRepository.save(order);
		Order foundOrder = orderRepository.findById(uuid).get();
		var orders = orderRepository.findAll();

		orderRepository.findAllByOrderStatus(OrderStatus.OPENED);
		orderRepository.findAllByOrderStatusOrderByOrderDatetime(OrderStatus.OPENED);

		orderRepository.findByMemo("메모메모");
	}
}