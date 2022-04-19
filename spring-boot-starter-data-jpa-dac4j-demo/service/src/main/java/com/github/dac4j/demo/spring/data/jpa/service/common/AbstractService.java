package com.github.dac4j.demo.spring.data.jpa.service.common;

import java.util.List;
import java.util.Optional;

public interface AbstractService<D, I> {

    List<D> findAll();
    D findOne(I id);

}
