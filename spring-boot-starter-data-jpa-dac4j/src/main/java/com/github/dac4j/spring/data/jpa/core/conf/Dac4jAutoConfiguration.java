package com.github.dac4j.spring.data.jpa.core.conf;

import com.github.dac4j.spring.data.jpa.core.factory.AccessControlJpaQueryMethodFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * DAC4J auto configuration class.
 *
 * @author Fahd Arhalai
 */
@Configuration
@ComponentScan("com.github.dac4j.spring.data.jpa")
@EnableJpaRepositories(basePackages = "com.github.dac4j.spring.data.jpa.dao")
@EntityScan("com.github.dac4j.spring.data.jpa.domain")
@ConditionalOnProperty(value = "dac4j.enabled", havingValue = "true", matchIfMissing = true)
public class Dac4jAutoConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean(name = "jpaQueryMethodFactory")
    public AccessControlJpaQueryMethodFactory jpaQueryMethodFactory() {
        return new AccessControlJpaQueryMethodFactory(PersistenceProvider.fromEntityManager(entityManager));
    }

}
