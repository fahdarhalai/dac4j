package com.github.dac4j.demo.spring.data.jpa.service.impl;

import com.github.dac4j.demo.spring.data.jpa.dao.CountryDao;
import com.github.dac4j.demo.spring.data.jpa.domain.CountryEntity;
import com.github.dac4j.demo.spring.data.jpa.service.CountryService;
import com.github.dac4j.demo.spring.data.jpa.service.dto.CountryDto;
import com.github.dac4j.demo.spring.data.jpa.service.exception.DataNotFoundException;
import com.github.dac4j.demo.spring.data.jpa.service.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao dao;

    @Autowired
    private CountryMapper mapper;

    @Override
    @Transactional
    public List<CountryDto> findAll() {
        List<CountryEntity> entities = dao.findAll();
        return mapper.toDto(entities);
    }

    @Override
    @Transactional
    public CountryDto findOne(Long aLong) {
        return dao.findById(aLong).map(mapper::toDto).orElseThrow(DataNotFoundException::new);
    }
}
