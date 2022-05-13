package com.example.practice.order;

import static com.example.practice.domain.order.OrderStatus.*;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice.domain.order.Member;
import com.example.practice.domain.order.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class OrderPersistenceTest {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Test
	void Member_저장() {
		var entityManager = entityManagerFactory.createEntityManager();
		var transaction = entityManager.getTransaction();
		var member = new Member();
		member.setName("hyebpark");
		member.setNickName("hyeb");
		member.setAddress("부산시 몰랑몰랑");
		member.setAge(12);

		transaction.begin();
		entityManager.persist(member);
		transaction.commit();

	}

	@Test
	void 잘못된_설계() {
		Member member = new Member();
		member.setName("hyebpark");
		member.setAddress("부산시 몰라몰랑.");
		member.setAge(12);
		member.setNickName("hyeb");
		member.setDescription("냥냥");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(member);
		Member memberEntity = entityManager.find(Member.class, 1L);

		Order order = new Order();
		order.setUuid(UUID.randomUUID().toString());
		order.setOrderDatetime(LocalDateTime.now());
		order.setOrderStatus(OPENED);
		order.setMemo("문 앞에 두고 가주세요~");
		// order.setMemberId(memberEntity.getId()); // 외래키를 직접 지정

		entityManager.persist(order);
		transaction.commit();

		Order orderEntity = entityManager.find(Order.class, order.getUuid());
		// FK 를 이용해 회원 다시 조회
		// Member orderMemberEntity = entityManager.find(Member.class, orderEntity.getMemberId());
		// orderEntity.getMember() // 객체중심 설계라면 이렇게 해야하지 않을까?
		// log.info("nick : {}", orderMemberEntity.getNickName());
	}

	@Test
	void 연관관계_테스트() {
		var entityManager = entityManagerFactory.createEntityManager();
		var transaction = entityManager.getTransaction();

		transaction.begin();

		Member member = new Member();
		member.setName("hyebpark");
		member.setNickName("hyeb");
		member.setAddress("부산시 몰랑몰랑");
		member.setAge(12);

		entityManager.persist(member);
		Order order = new Order();
		order.setUuid(UUID.randomUUID().toString());
		order.setOrderStatus(OPENED);
		order.setOrderDatetime(LocalDateTime.now());
		order.setMemo("문 앞에 두고 가주세요~");

		entityManager.persist(order);
		transaction.commit();

		entityManager.clear();
		entityManager.find(Order.class, order.getUuid());

		log.info("{}", order.getMember().getNickName());
		log.info("{}", order.getMember().getOrders().size());
	}
}
