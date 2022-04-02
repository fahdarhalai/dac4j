package com.github.fahdarhalai.demodac.service.mapper;

import com.github.fahdarhalai.demodac.domain.CountryEntity;
import com.github.fahdarhalai.demodac.service.dto.CountryDto;
import com.github.fahdarhalai.demodac.service.mapper.common.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {
        CityMapper.class
}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper extends AbstractMapper<CountryEntity, CountryDto> {

}
