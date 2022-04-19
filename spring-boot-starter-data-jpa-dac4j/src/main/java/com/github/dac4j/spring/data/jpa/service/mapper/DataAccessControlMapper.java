package com.github.dac4j.spring.data.jpa.service.mapper;

import com.github.dac4j.spring.data.jpa.service.dto.DataAccessControlDto;
import com.github.dac4j.spring.data.jpa.domain.DataAccessControlEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Data access control mapper.
 *
 * @author Fahd Arhalai
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DataAccessControlMapper {

    DataAccessControlEntity toEntity(DataAccessControlDto dto);

    DataAccessControlDto toDto(DataAccessControlEntity entity);

    List<DataAccessControlEntity> toEntity(List<DataAccessControlDto> dtoList);

    List<DataAccessControlDto> toDto(List<DataAccessControlEntity> entityList);

    void updateEntityFromDto(@MappingTarget DataAccessControlEntity entity, DataAccessControlDto dto);
}
