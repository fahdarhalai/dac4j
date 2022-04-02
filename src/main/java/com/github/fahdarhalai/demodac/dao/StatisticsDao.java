package com.github.fahdarhalai.demodac.dao;

import com.github.fahdarhalai.demodac.dao.common.AbstractDao;
import com.github.fahdarhalai.demodac.dao.repository.StatisticsRepository;
import com.github.fahdarhalai.demodac.domain.StatisticsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StatisticsDao extends AbstractDao<StatisticsEntity, Long> {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public JpaRepository<StatisticsEntity, Long> getJpaRepository() {
        return statisticsRepository;
    }

    public Optional<StatisticsEntity> findByCityId(Long cityId) {
        return statisticsRepository.findByCityId(cityId);
    }
}
