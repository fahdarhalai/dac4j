package com.github.dac4j.demo.spring.data.jpa.dao;

import com.github.dac4j.demo.spring.data.jpa.dao.repository.StatisticsRepository;
import com.github.dac4j.demo.spring.data.jpa.dao.common.AbstractDao;
import com.github.dac4j.demo.spring.data.jpa.domain.StatisticsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

@Repository
public class StatisticsDao extends AbstractDao<StatisticsEntity, StatisticsRepository, Long> {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public StatisticsRepository getJpaRepository() {
        return statisticsRepository;
    }

    public Optional<StatisticsEntity> findByCityId(Long cityId) {
        return statisticsRepository.findByCityId(cityId);
    }
}
