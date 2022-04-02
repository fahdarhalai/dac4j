package com.github.fahdarhalai.demodac.dao.repository;

import com.github.fahdarhalai.demodac.domain.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Long> {

    Optional<StatisticsEntity> findByCityId(Long cityId);
}
