package com.rajeshkawali.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author Rajesh_Kawali
 * 
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
    public Docket configApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rajeshkawali"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .groupName("MyGroup")
                .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring boot crud API service")
				.description("Spring boot service API reference for developer.")
				.license("Copyright (c) 2022 Rajesh or its subsidiaries. All Rights Reserved.")
				.version("1.0")
				.build();
	}
}