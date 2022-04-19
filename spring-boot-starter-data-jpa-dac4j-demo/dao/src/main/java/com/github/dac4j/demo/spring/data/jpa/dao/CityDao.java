package com.github.dac4j.demo.spring.data.jpa.dao;

import com.github.dac4j.demo.spring.data.jpa.dao.repository.CityRepository;
import com.github.dac4j.demo.spring.data.jpa.dao.common.AbstractDao;
import com.github.dac4j.demo.spring.data.jpa.domain.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityDao extends AbstractDao<CityEntity, CityRepository, Long> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityRepository getJpaRepository() {
        return cityRepository;
    }

}
