package com.example.practice;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice.domain.Customer;
import com.example.practice.domain.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PersistenceContextTest {

	@Autowired
	CustomerRepository repository;

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@BeforeEach
	void setUp() {
		repository.deleteAll();
	}

	@Test
	void 저장() {
		var entityManager = entityManagerFactory.createEntityManager();
		var transaction = entityManager.getTransaction();

		transaction.begin();

		Customer customer = new Customer(); // 비영속 상태
		customer.setId(1L);
		customer.setFirstName("hyeb");
		customer.setLastName("park");

		entityManager.persist(customer); // 영속화
		transaction.commit(); // entityManager.flush(), 쓰기지연
	}

	@Test
	void 조회_DB조회() {
		var entityManager = entityManagerFactory.createEntityManager();
		var transaction = entityManager.getTransaction();

		transaction.begin();

		Customer customer = new Customer(); // 비영속 상태
		customer.setId(1L);
		customer.setFirstName("hyeb");
		customer.setLastName("park");

		entityManager.persist(customer); // 영속화
		transaction.commit(); // entityManager.flush(), 쓰기지연

		entityManager.detach(customer); // 준영속 상태로 변경
		var foundCustomer = entityManager.find(Customer.class, 1L);
		log.info("{} {}", foundCustomer.getFirstName(), foundCustomer.getLastName());
	}

	@Test
	void 조회_1차캐시() {
		var entityManager = entityManagerFactory.createEntityManager();
		var transaction = entityManager.getTransaction();

		transaction.begin();

		Customer customer = new Customer(); // 비영속 상태
		customer.setId(1L);
		customer.setFirstName("hyeb");
		customer.setLastName("park");

		entityManager.persist(customer); // 영속화
		transaction.commit(); // entityManager.flush(), 쓰기지연

		var foundCustomer = entityManager.find(Customer.class, 1L);
		log.info("{} {}", foundCustomer.getFirstName(), foundCustomer.getLastName());
	}

	@Test
	void 수정() {
		var entityManager = entityManagerFactory.createEntityManager();
		var transaction = entityManager.getTransaction();

		transaction.begin();

		Customer customer = new Customer(); // 비영속 상태
		customer.setId(1L);
		customer.setFirstName("hyeb");
		customer.setLastName("park");

		entityManager.persist(customer); // 영속화
		transaction.commit(); // entityManager.flush(), 쓰기지연

		transaction.begin();
		customer.setFirstName("test");
		customer.setLastName("update");
		transaction.commit();
	}

	@Test
	void 삭제() {
		var entityManager = entityManagerFactory.createEntityManager();
		var transaction = entityManager.getTransaction();

		transaction.begin();

		Customer customer = new Customer(); // 비영속 상태
		customer.setId(1L);
		customer.setFirstName("hyeb");
		customer.setLastName("park");

		entityManager.persist(customer); // 영속화
		transaction.commit(); // entityManager.flush(), 쓰기지연

		transaction.begin();
		entityManager.remove(customer);
	}
}
