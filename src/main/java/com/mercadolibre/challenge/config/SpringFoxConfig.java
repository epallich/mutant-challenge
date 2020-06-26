package com.mercadolibre.challenge.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	// @formatter:off
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Mutant API",
            "This is the Mutant API service for Magneto with the following services&#58;\n" +
            "      - Given a DNA returns if belong to a Mutant or not\n" +
            "      - Stats for the processed DNAs",
            "V1.0",
            null,
            new Contact("Ezequiel", "", "epallich@gmail.com"),
            null,
            null,
            new ArrayList<>());
	
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(DEFAULT_API_INFO)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build();                                           
    }
	
	// @formatter:on
}
