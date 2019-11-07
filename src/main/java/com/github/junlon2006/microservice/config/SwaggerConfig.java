package com.github.junlon2006.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author junlon2006
 * @date 2019-08-03 12:30:00
 * @since jdk8
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.junlon2006.microservice"))
                .paths(PathSelectors.any()).build().useDefaultResponseMessages(false);
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("micro-service API")
                .description("Micro-service Swagger API 文档")
                .termsOfServiceUrl("http://gitlab.junlon2006.com/junlon2006/micro-service")
                .version("1.0")
                .contact(new Contact("junlon2006", "https://github.com/junlon2006",
                        "shangjinlong@unisound.com"))
                .build();
    }
}
