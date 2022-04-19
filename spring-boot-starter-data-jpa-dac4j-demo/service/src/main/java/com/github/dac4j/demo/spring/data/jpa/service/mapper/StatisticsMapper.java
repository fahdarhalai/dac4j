package com.github.dac4j.demo.spring.data.jpa.service.mapper;

import com.github.dac4j.demo.spring.data.jpa.service.dto.StatisticsDto;
import com.github.dac4j.demo.spring.data.jpa.service.mapper.common.AbstractMapper;
import com.github.dac4j.demo.spring.data.jpa.domain.StatisticsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatisticsMapper extends AbstractMapper<StatisticsEntity, StatisticsDto> {

    @Override
    @Mapping(target = "cityId", expression = "java(mapCityToCityId(entity))")
    StatisticsDto toDto(StatisticsEntity entity);

    default Long mapCityToCityId(StatisticsEntity statistics) {
        return statistics.getCity().getId();
    }
}
