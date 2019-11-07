package com.github.junlon2006.microservice.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author junlon2006
 * @date 2019-08-03 15:15:00
 * @since jdk8
 */
@Configuration
public class FlywayConfig {
    @Autowired
    DataSource dataSource;

    @Bean
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setSqlMigrationPrefix("V");
        flyway.setBaselineOnMigrate(true);
        flyway.setOutOfOrder(true);
        flyway.repair();
        flyway.migrate();
        return flyway;
    }
}
