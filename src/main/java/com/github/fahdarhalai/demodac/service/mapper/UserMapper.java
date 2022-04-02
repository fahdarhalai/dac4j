package com.github.fahdarhalai.demodac.service.mapper;

import com.github.fahdarhalai.demodac.domain.UserEntity;
import com.github.fahdarhalai.demodac.service.dto.UserDto;
import com.github.fahdarhalai.demodac.service.mapper.common.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends AbstractMapper<UserEntity, UserDto> {
}
