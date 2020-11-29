package com.epam.esm.config;

import com.epam.esm.db.config.DbApiConfig;
import com.epam.esm.db.service.DbImplConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.epam.esm.rest")
@Import(DbImplConfig.class)
public class RestConfig {
}
