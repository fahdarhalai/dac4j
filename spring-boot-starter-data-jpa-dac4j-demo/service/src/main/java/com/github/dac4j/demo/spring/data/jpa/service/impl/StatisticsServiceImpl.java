package com.github.dac4j.demo.spring.data.jpa.service.impl;

import com.github.dac4j.demo.spring.data.jpa.dao.StatisticsDao;
import com.github.dac4j.demo.spring.data.jpa.service.StatisticsService;
import com.github.dac4j.demo.spring.data.jpa.service.dto.StatisticsDto;
import com.github.dac4j.demo.spring.data.jpa.service.exception.DataNotFoundException;
import com.github.dac4j.demo.spring.data.jpa.service.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao dao;

    @Autowired
    private StatisticsMapper mapper;

    @Override
    @Transactional
    public List<StatisticsDto> findAll() {
        return mapper.toDto(dao.findAll());
    }

    @Override
    @Transactional
    public StatisticsDto findOne(Long aLong) {
        return dao.findById(aLong).map(mapper::toDto).orElseThrow(DataNotFoundException::new);
    }

    @Override
    @Transactional
    public StatisticsDto findByCity(Long cityId) {
        return dao.findByCityId(cityId).map(mapper::toDto).orElseThrow(DataNotFoundException::new);
    }
}
