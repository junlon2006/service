package com.github.junlon2006.microservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author junlon2006
 * @date 2019-08-03 15:13
 * @since jdk8
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.github.junlon2006.microservice.mapper")
public class MybatisPlusConfig {
}
