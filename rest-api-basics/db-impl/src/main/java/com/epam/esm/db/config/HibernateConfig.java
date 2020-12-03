package com.epam.esm.db.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@ComponentScan(basePackages = "com.epam.esm.db")
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
public class HibernateConfig {

    private static final Logger LOGGER = LogManager.getLogger(HibernateConfig.class);

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.database-platform"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        LOGGER.info("Configuring hikari cp");
        HikariConfig hc = new HikariConfig();
        hc.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
        hc.setJdbcUrl(environment.getRequiredProperty("spring.datasource.url"));
        hc.setUsername(environment.getRequiredProperty("spring.datasource.username"));
        hc.setPassword(environment.getRequiredProperty("spring.datasource.password"));
        return new HikariDataSource(hc);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LOGGER.info("create session factory");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("classpath:com.epam.esm");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}

