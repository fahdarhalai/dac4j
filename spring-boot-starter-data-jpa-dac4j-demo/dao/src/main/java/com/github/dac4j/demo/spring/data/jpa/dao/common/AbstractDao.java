package com.github.dac4j.demo.spring.data.jpa.dao.common;

import com.github.dac4j.demo.spring.data.jpa.domain.common.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<E extends AbstractEntity, R extends JpaRepository<E, I>, I> {

    @PersistenceContext
    private EntityManager entityManager;

    public abstract R getJpaRepository();

    public Optional<E> findById(I id) {
        return getJpaRepository().findById(id);
    }

    public List<E> findAll() {
        return getJpaRepository().findAll();
    }


}
