package com.example.practice.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.practice.exception.NotFoundException;
import com.example.practice.order.OrderConverter;
import com.example.practice.order.OrderRepository;
import com.example.practice.order.controller.OrderDto;

@Transactional
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderConverter orderConverter;

	@Transactional
	public String save(OrderDto orderDto) {
		// tx.begin()
		// 1. dto -> entity 변환 (준영속)
		var order = orderConverter.convertOrder(orderDto);
		// 2. orderRepository.save() -> 영속화
		var entity = orderRepository.save(order);
		// 3. 결과 반환
		return entity.getUuid();
	}

	public OrderDto findOne(String uuid) throws NotFoundException {
		return orderRepository.findById(uuid)
			.map(orderConverter::convertOrderDto)
			.orElseThrow(() -> new NotFoundException("주문을 찾을 수 없습니다."));
	}

	public Page<OrderDto> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable)
			.map(orderConverter::convertOrderDto);
	}
}