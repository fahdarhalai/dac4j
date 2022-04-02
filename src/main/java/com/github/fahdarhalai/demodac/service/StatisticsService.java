package com.github.fahdarhalai.demodac.service;

import com.github.fahdarhalai.demodac.service.common.AbstractService;
import com.github.fahdarhalai.demodac.service.dto.StatisticsDto;

import java.util.Optional;

public interface StatisticsService extends AbstractService<StatisticsDto, Long> {

    Optional<StatisticsDto> findByCity(Long cityId);
}
