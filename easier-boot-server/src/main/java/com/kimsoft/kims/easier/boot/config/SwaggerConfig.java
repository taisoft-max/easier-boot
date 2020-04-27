package com.kimsoft.kims.easier.boot.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Kimi
 * @date 2020/4/17
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enable: true}")
    private boolean swaggerEnable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .directModelSubstitute(LocalDate.class, Date.class)
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .select()
                .paths(this.paths())
                .build()
                .apiInfo(this.apiInfo())
                .enable(swaggerEnable);

    }

    private Predicate<String> paths() {
        return Predicates.and(PathSelectors.ant("/middleware/**"),
                              Predicates.not(PathSelectors.regex("/error")));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Kimi Infrastructure Middleware Server - Easy Boot")
                .description("Kimi Infrastructure Middleware Server - Easy Boot")
                .license("MIT license")
                .version("1.0.0-SNAPSHOT")
                .build();
    }
}
