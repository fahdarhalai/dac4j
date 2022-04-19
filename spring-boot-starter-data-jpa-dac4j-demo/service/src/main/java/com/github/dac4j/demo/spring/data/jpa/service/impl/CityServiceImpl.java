package com.github.dac4j.demo.spring.data.jpa.service.impl;

import com.github.dac4j.demo.spring.data.jpa.dao.CityDao;
import com.github.dac4j.demo.spring.data.jpa.service.CityService;
import com.github.dac4j.demo.spring.data.jpa.service.dto.CityDto;
import com.github.dac4j.demo.spring.data.jpa.service.exception.DataNotFoundException;
import com.github.dac4j.demo.spring.data.jpa.service.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao dao;

    @Autowired
    private CityMapper mapper;

    @Override
    @Transactional
    public List<CityDto> findAll() {
        return mapper.toDto(dao.findAll());
    }

    @Override
    @Transactional
    public CityDto findOne(Long aLong) {
        return dao.findById(aLong).map(mapper::toDto).orElseThrow(DataNotFoundException::new);
    }
}
