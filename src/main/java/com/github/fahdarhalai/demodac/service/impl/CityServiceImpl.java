package com.github.fahdarhalai.demodac.service.impl;

import com.github.fahdarhalai.demodac.dao.CityDao;
import com.github.fahdarhalai.demodac.service.CityService;
import com.github.fahdarhalai.demodac.service.dto.CityDto;
import com.github.fahdarhalai.demodac.service.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao dao;

    @Autowired
    private CityMapper mapper;

    @Override
    public List<CityDto> findAll() {
        return mapper.toDto(dao.findAll());
    }

    @Override
    public Optional<CityDto> findOne(Long aLong) {
        return dao.findById(aLong).map(mapper::toDto);
    }
}
