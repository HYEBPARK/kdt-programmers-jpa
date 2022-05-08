package com.example.practice;

import java.sql.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootTest
public class JDBCTest {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static final String USER = "sa";
    static final String PASS = "";

    static final String DROP_TABLE_SQL = "DROP TABLE customers IF EXISTS";
    static final String CREATE_TABLE_SQL = "CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))";
    static final String INSERT_SQL = "INSERT INTO customers (id, first_name, last_name) VALUES (1, 'honggu', 'kang')";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void JDBC_sample() {
        try {
            Class.forName(JDBC_DRIVER); // jdbc 구현체의 driver
            var connection = DriverManager.getConnection(DB_URL, USER, PASS);
            log.info("GET CONNECTION");
            // rdb와 connection을 맺게 됨

            var statement = connection.createStatement();
            // connection 후 통신을 위한 statement 객체 만듬

            statement.executeUpdate(DROP_TABLE_SQL);
            statement.executeUpdate(CREATE_TABLE_SQL);
            log.info("CREATED TABLE");

            statement.executeUpdate(INSERT_SQL);
            log.info("INSERTED CUSTOMER INFORMATION");
            // query using statement

            var resultSet = statement.executeQuery("SELECT * FROM customers WHERE id=1");
            // resultset을 반환 받고

            while (resultSet.next()) {
                var fullName =
                    resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                log.info("CUSTOEMR FULL_NAME : {}", fullName);
            }
            // resultSet을 통해 데이터를 받아옴

            statement.close();
            connection.close();
            // close
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void JDBCTemplate_sample() {
        jdbcTemplate.update(DROP_TABLE_SQL);
        jdbcTemplate.update(CREATE_TABLE_SQL);
        log.info("CREATED TABLE USING JDBC TEMPLATE");

        jdbcTemplate.update(INSERT_SQL);
        log.info("INSERTED CUSTOMER INFORMATION USING JDBC TEMPLATE");

        var fullName = jdbcTemplate.queryForObject("SELECT * FROM customers WHERE id = 1",
            (rs, i) -> rs.getString("first_name") + " " + rs.getString("last_name"));
        log.info("FULL NAME : {}", fullName);
    }
}
