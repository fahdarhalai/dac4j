package com.github.dac4j.demo.spring.data.jpa.service.mapper;

import com.github.dac4j.demo.spring.data.jpa.domain.CityEntity;
import com.github.dac4j.demo.spring.data.jpa.service.dto.CityDto;
import com.github.dac4j.demo.spring.data.jpa.service.mapper.common.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", uses = {
        StatisticsMapper.class
}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper extends AbstractMapper<CityEntity, CityDto> {

    @Override
    @Mapping(target = "countryId", expression = "java(mapCountryToCountryId(entity))")
    CityDto toDto(CityEntity entity);

    default Long mapCountryToCountryId(CityEntity city) {
        return city.getCountry().getId();
    }
}
