package com.example.practice;

import com.example.practice.repository.CustomerRepository;
import com.example.practice.repository.domain.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
public class JPATest {

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void INSERT_TEST() {
        //given
        CustomerEntity customer = new CustomerEntity();
        customer.setId(1L);
        customer.setFirstName("hyeb");
        customer.setLastName("park");
        customer.setAge(12);
        //when
        customerRepository.save(customer);
        //then
        var entity = customerRepository.findById(1L).get();
        log.info("{} {}", entity.getFirstName(), entity.getLastName());
        log.info("age : {}", entity.getAge());
    }

    @Test
    @Transactional // 영속성 컨텍스트 안에서 관리를 하겠다.
    void UPDATE_TEST() {
        //given
        CustomerEntity customer = new CustomerEntity();
        customer.setId(1L);
        customer.setFirstName("hyeb");
        customer.setLastName("park");
        customerRepository.save(customer);

        //when
        var entity = customerRepository.findById(1L).get();
        entity.setFirstName("mong");
        entity.setLastName("mong");

        var updated = customerRepository.findById(1L).get();
        log.info("{} {}", updated.getFirstName(), updated.getLastName());
    }
}
