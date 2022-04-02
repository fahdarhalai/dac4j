package com.github.fahdarhalai.demodac.service.mapper.common;

import java.util.List;

public interface AbstractMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

}
