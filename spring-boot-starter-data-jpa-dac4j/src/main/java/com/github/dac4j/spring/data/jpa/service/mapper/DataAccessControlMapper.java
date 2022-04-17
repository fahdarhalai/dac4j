package com.github.dac4j.spring.data.jpa.service.mapper;

import com.github.dac4j.spring.data.jpa.service.dto.DataAccessControlDto;
import com.github.dac4j.spring.data.jpa.domain.DataAccessControlEntity;
import com.github.dac4j.spring.data.jpa.service.mapper.common.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DataAccessControlMapper extends AbstractMapper<DataAccessControlEntity, DataAccessControlDto> {

}
