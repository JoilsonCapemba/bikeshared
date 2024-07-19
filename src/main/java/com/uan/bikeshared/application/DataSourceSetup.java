package com.uan.bikeshared.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(basePackages = {"com.uan.bikeshared.repository"})
@ComponentScan(value = "com.uan.bikeshared.*")
@EntityScan(basePackages = {"com.uan.bikeshared.model"})
public class DataSourceSetup {
    @Value("${spring.datasource.url}")
    String databaseUrl;

    @Value("${spring.datasource.username}")
    String databaseUser;

    @Value("${spring.datasource.password}")
    String databasePassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUser);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }
}
