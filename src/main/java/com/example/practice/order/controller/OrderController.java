package com.example.practice.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.exception.NotFoundException;
import com.example.practice.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ExceptionHandler(NotFoundException.class)
	public ApiResponse<String> notFoundHandler(NotFoundException e) {
		return ApiResponse.fail(404, e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ApiResponse<String> internalServerErrorHandler(Exception e) {
		return ApiResponse.fail(500, e.getMessage());
	}

	@PostMapping("/orders")
	public ApiResponse<String> save(@RequestBody OrderDto orderDto) {
		var uuid = orderService.save(orderDto);

		return ApiResponse.ok(uuid);
	}

	@GetMapping("/orders/{uuid}")
	public ApiResponse<OrderDto> getOne(@PathVariable String uuid) throws NotFoundException {
		var one = orderService.findOne(uuid);

		return ApiResponse.ok(one);
	}

	@GetMapping("/orders")
	public ApiResponse<Page<OrderDto>> getAll(Pageable pageable) {
		Page<OrderDto> all = orderService.findAll(pageable);

		return ApiResponse.ok(all);
	}
}