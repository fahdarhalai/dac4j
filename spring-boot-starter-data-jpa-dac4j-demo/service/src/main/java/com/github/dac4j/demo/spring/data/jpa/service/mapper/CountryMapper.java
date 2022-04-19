package com.github.dac4j.demo.spring.data.jpa.service.mapper;

import com.github.dac4j.demo.spring.data.jpa.domain.CountryEntity;
import com.github.dac4j.demo.spring.data.jpa.service.dto.CountryDto;
import com.github.dac4j.demo.spring.data.jpa.service.mapper.common.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {
        CityMapper.class
}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper extends AbstractMapper<CountryEntity, CountryDto> {

}
