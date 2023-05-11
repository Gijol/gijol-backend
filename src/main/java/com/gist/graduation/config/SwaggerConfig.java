package com.gist.graduation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Value("${swagger.host:localhost:8080}")
    private String host;
    @Value("${swagger.protocol:http}")
    private String protocol;

    @Bean
    public Docket api() {
        RequestParameter requestParameter = new RequestParameterBuilder()
                .name("Authorization") //헤더 이름
                .description("Google Id Token")
                .in(ParameterType.HEADER)
                .query(q -> q.defaultValue("Bearer "))
                .required(false)
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .globalRequestParameters(Collections.singletonList(requestParameter))
                .apiInfo(apiInfo())
                .host(host)
                .protocols(Collections.singleton(protocol))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gist.graduation"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("GIJOL API")
                .version("1.0.0")
                .description("GIJOL API 문서입니다.")
                .build();
    }
}
