package com.example.repository;

import com.example.entity.Person;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonRepositoryTest {

    private final static Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Resource
    private PersonRepository repository;


    @BeforeEach
    void setup() {
        logger.info("HelloTest.setup");
    }

    @AfterEach
    void teardown() {
        logger.info("HelloTest.teardown");
    }

    @Nested
    @SpringBootTest
    class FindAll {

        @BeforeEach
        void setup() {
            logger.info("FindOne.setup");
            Person p = new Person();
            p.setId(1);
            p.setName("foo");
            p.setGender("female");

            repository.save(p);
            p.setId(2);
            p.setName("bar");
            p.setGender("male");
            repository.save(p);
        }

        @AfterEach
        void teardown() {
            logger.info("FindOne.teardown");
        }

        @Test
        void all() {
            repository.findAll().forEach(e -> logger.info(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
        }
    }

}