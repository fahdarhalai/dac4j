package com.github.dac4j.demo.spring.data.jpa.dao.repository;

import com.github.dac4j.demo.spring.data.jpa.domain.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Long> {

    Optional<StatisticsEntity> findByCityId(Long cityId);
}
