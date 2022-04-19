package com.github.dac4j.demo.spring.data.jpa.api.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableWebMvc
@Configuration
public class SpringFoxConfiguration {

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("DAC4J")
                .version("1.0.0")
                .license(null)
                .licenseUrl(null)
                .termsOfServiceUrl(null)
                .description("Data Access Control for Java. Default user is Fahd Arhalai (id = 1).")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.dac4j"))
                .paths(PathSelectors.any())
                .build();
    }
}