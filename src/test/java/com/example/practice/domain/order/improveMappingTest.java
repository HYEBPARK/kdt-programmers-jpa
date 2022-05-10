package com.example.practice.domain.order;

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
		food.setPrice(1000);
		food.setStockQuantity(100);
		food.setChef("백종원");

		entityManager.persist(food);

		transaction.commit();
	}
}