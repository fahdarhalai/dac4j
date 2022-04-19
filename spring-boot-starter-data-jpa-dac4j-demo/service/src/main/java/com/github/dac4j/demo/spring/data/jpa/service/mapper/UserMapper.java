package com.github.dac4j.demo.spring.data.jpa.service.mapper;

import com.github.dac4j.demo.spring.data.jpa.service.dto.UserDto;
import com.github.dac4j.demo.spring.data.jpa.domain.UserEntity;
import com.github.dac4j.demo.spring.data.jpa.service.mapper.common.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends AbstractMapper<UserEntity, UserDto> {
}
