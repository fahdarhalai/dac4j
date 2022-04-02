package com.github.fahdarhalai.demodac.service.impl;

import com.github.fahdarhalai.demodac.dao.CountryDao;
import com.github.fahdarhalai.demodac.service.CountryService;
import com.github.fahdarhalai.demodac.service.dto.CountryDto;
import com.github.fahdarhalai.demodac.service.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao dao;

    @Autowired
    private CountryMapper mapper;

    @Override
    public List<CountryDto> findAll() {
        return mapper.toDto(dao.findAll());
    }

    @Override
    public Optional<CountryDto> findOne(Long aLong) {
        return dao.findById(aLong).map(mapper::toDto);
    }
}
