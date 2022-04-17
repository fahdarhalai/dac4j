package com.github.dac4j.spring.data.jpa.core.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan("com.github.dac4j.spring.data.jpa")
@EnableJpaRepositories(basePackages = "com.github.dac4j.spring.data.jpa.dao")
@EntityScan("com.github.dac4j.spring.data.jpa.domain")
@ConditionalOnProperty(value = "dac4j.enabled", havingValue = "true", matchIfMissing = true)
public class Dac4jAutoConfiguration {


}
