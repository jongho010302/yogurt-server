package com.yogurt.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Group BRS contains operations related to reservations and agency mangement
     */
    @Bean
    public Docket swaggerYogurtApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Yogurt")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yogurt.controller.v1.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    /**
     * Group User contains operations related to user mangement such as login/logout
     */
    @Bean
    public Docket swaggerUserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("yogurt v1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yogurt.controller.v1.ap1"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Yogurt Studio - REST APIs")
                .description("Yogurt Studio Rest API for v1").termsOfServiceUrl("")
                .contact(new Contact("Jeon Jongho", "https://github.com/yogurt-studio", "yogurtstudio0302@gmail.com"))
                .license("Yogurt")
                .licenseUrl("https://github.com/yogurt-studio/yogurt-server/LICENSE-2.0")
                .version("0.0.1")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("accessToken", "Authorization", "header");
    }
}
