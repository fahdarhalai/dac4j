package com.github.dac4j.demo.spring.data.jpa.dao.conf;

import com.github.dac4j.spring.data.jpa.core.factory.AccessControlJpaRepositoryFactoryBean;
import com.github.dac4j.spring.data.jpa.core.repository.AccessControlSimpleJpaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.github.dac4j.demo.spring.data.jpa.dao",
        repositoryBaseClass = AccessControlSimpleJpaRepository.class,
        repositoryFactoryBeanClass = AccessControlJpaRepositoryFactoryBean.class
)
@EntityScan("com.github.dac4j.demo.spring.data.jpa.domain")
public class DaoConfiguration {}

