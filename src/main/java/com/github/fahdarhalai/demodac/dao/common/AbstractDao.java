package com.github.fahdarhalai.demodac.dao.common;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<E, I> {

    @PersistenceContext
    private EntityManager entityManager;

    public abstract JpaRepository<E, I> getJpaRepository();

    public Optional<E> findById(I id) {
        return getJpaRepository().findById(id);
    }

    public List<E> findAll() {
        return getJpaRepository().findAll();
    }

}
