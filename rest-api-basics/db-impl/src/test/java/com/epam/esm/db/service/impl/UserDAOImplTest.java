package com.epam.esm.db.service.impl;

import com.epam.esm.db.config.HibernateConfig;
import com.epam.esm.db.service.UserDAO;
import com.epam.esm.db.service.config.H2JpaConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest(excludeAutoConfiguration = HibernateConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class UserDAOImplTest {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImplTest.class);
    @Autowired
    private EntityManager entityManager;

    private UserDAO userDAO;
    @Autowired
    public UserDAOImplTest() {
        userDAO = new UserDAOImpl();
    }

    @Test
    void assertDaoNotNull() {
        LOGGER.info(entityManager.getProperties().toString());
        assertNotNull(userDAO);
    }

//    @Test
//    void findAll() {
//        userDAO.findAll(1L,1L);
//
//    }
//
//    @Test
//    void findById() {
//    }
}