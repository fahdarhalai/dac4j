package com.github.dac4j.demo.spring.data.jpa.service;

import com.github.dac4j.demo.spring.data.jpa.service.common.AbstractService;
import com.github.dac4j.demo.spring.data.jpa.service.dto.StatisticsDto;

public interface StatisticsService extends AbstractService<StatisticsDto, Long> {

    StatisticsDto findByCity(Long cityId);
}
