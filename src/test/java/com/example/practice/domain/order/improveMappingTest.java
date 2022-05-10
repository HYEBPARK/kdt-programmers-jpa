package com.example.practice.domain.order;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class improveMappingTest {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Test
	void inheritance_test() {
		var entityManager = entityManagerFactory.createEntityManager();
		var transaction = entityManager.getTransaction();

		transaction.begin();

		Food food = new Food();
		food.setPrice(2000);
		food.setStockQuantity(200);
		food.setChef("백종원");

		entityManager.persist(food);

		transaction.commit();
	}

	@Test
	void mapped_super_class_test() {
		var entityManager = entityManagerFactory.createEntityManager();
		var transaction = entityManager.getTransaction();

		transaction.begin();

		Order order = new Order();
		order.setUuid(UUID.randomUUID().toString());
		order.setOrderStatus(OrderStatus.OPENED);
		order.setMemo("메모메모메모");
		order.setOrderDatetime(LocalDateTime.now());

		order.setCreatedBy("hyebpark");
		order.setCreatedAt(LocalDateTime.now());

		entityManager.persist(order);
		transaction.commit();
	}
}
