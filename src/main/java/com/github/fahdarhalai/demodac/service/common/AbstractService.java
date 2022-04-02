package com.github.fahdarhalai.demodac.service.common;

import java.util.List;
import java.util.Optional;

public interface AbstractService<D, I> {

    List<D> findAll();
    Optional<D> findOne(I id);

}
