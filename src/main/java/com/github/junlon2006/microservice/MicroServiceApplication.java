package com.github.junlon2006.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author junlon2006
 * @since jdk8
 * @date 2019-08-03 12:00:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@FeignClient
public class MicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceApplication.class, args);
	}

}
