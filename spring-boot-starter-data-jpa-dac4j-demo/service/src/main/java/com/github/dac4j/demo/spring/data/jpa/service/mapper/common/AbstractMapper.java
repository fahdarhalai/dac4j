package com.github.dac4j.demo.spring.data.jpa.service.mapper.common;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface AbstractMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

    void updateEntityFromDto(@MappingTarget E entity, D dto);
}
