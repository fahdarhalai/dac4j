package com.github.dac4j.demo.spring.data.jpa.dao;

import com.github.dac4j.demo.spring.data.jpa.dao.repository.CountryRepository;
import com.github.dac4j.demo.spring.data.jpa.dao.common.AbstractDao;
import com.github.dac4j.demo.spring.data.jpa.domain.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CountryDao extends AbstractDao<CountryEntity, CountryRepository, Long> {

    @Autowired
    private CountryRepository countryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CountryRepository getJpaRepository() {
        return countryRepository;
    }

}
