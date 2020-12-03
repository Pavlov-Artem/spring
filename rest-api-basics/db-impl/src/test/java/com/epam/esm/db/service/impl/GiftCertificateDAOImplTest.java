package com.epam.esm.db.service.impl;

import com.epam.esm.db.service.GiftCertificateDAO;
import com.epam.esm.db.service.config.H2JpaConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


public class
GiftCertificateDAOImplTest {

    private EmbeddedDatabase embeddedDatabase;
    private GiftCertificateDAO giftCertificateDAO;

    @BeforeEach
    void setUp() {

        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();
        giftCertificateDAO = new GiftCertificateDAOImpl(embeddedDatabase);

    }

    @AfterEach
    void shutdown() {
        embeddedDatabase.shutdown();
    }


    @Test
    void findAll() {

        int expectedSize = 5;
        Assertions.assertEquals(expectedSize, giftCertificateDAO.findAll().size());

    }

    @Test
    void findAllWithCriteria() {



    }
//
//    @Test
//    void createEntity() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void updateCertificate() {
//    }
//
//    @Test
//    void updateCertificateTags() {
//    }
//
//    @Test
//    void deleteCertificate() {
//    }
//
//    @Test
//    void addCertificateTags() {
//    }


}
