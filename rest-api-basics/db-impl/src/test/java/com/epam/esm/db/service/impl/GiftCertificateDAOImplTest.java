package com.epam.esm.db.service.impl;

import com.epam.esm.db.service.GiftCertificateDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class GiftCertificateDAOImplTest {


    private EmbeddedDatabase embeddedDatabase;
    private GiftCertificateDAO giftCertificateDAO;

    @BeforeEach
    void setUp(){

        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();
        giftCertificateDAO = new GiftCertificateDAOImpl(embeddedDatabase);

    }

    @AfterEach
    void shutdown(){
        embeddedDatabase.shutdown();
    }


    @Test
    void dbTest(){

        giftCertificateDAO.findAll().forEach(giftCertificate -> System.out.println(giftCertificate.toString()));




    }



}
