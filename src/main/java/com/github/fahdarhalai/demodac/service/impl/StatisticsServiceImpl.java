package com.github.fahdarhalai.demodac.service.impl;

import com.github.fahdarhalai.demodac.dao.StatisticsDao;
import com.github.fahdarhalai.demodac.service.StatisticsService;
import com.github.fahdarhalai.demodac.service.dto.StatisticsDto;
import com.github.fahdarhalai.demodac.service.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao dao;

    @Autowired
    private StatisticsMapper mapper;

    @Override
    public List<StatisticsDto> findAll() {
        return mapper.toDto(dao.findAll());
    }

    @Override
    public Optional<StatisticsDto> findOne(Long aLong) {
        return dao.findById(aLong).map(mapper::toDto);
    }

    @Override
    public Optional<StatisticsDto> findByCity(Long cityId) {
        return dao.findByCityId(cityId).map(mapper::toDto);
    }
}
