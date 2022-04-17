package com.github.dac4j.spring.data.jpa.core.repository;

import com.github.dac4j.spring.data.jpa.core.annotation.AccessControl;
import com.github.dac4j.spring.data.jpa.core.utils.AccessControlSpecificationUtil;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.*;

import javax.persistence.*;
import java.util.Optional;

/**
 * DAC4J implementation of {@link org.springframework.data.jpa.repository.JpaRepository}. It is based
 * on {@link SimpleJpaRepository}.
 * @param <T> Entity class type
 * @param <ID> Entity id class type
 */
public class AccessControlSimpleJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    private EntityManager em;
    private final JpaEntityInformation<T, ?> entityInformation;

    AccessControlSimpleJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
        this.entityInformation = entityInformation;
    }

    AccessControlSimpleJpaRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, em);
    }

    @Override
    protected <S extends T> TypedQuery<S> getQuery(Specification<S> spec, Class<S> domainClass, Sort sort) {
        if(domainClass.isAnnotationPresent(AccessControl.class)) {
            Specification<T> acSpec = AccessControlSpecificationUtil.getAccessControlSpecification(em, getDomainClass());
            // TODO: verify casting is always possible
            spec = spec != null
                    ? spec.and((Specification<S>) acSpec)
                    : (Specification<S>) acSpec;
        }
        return super.getQuery(spec, domainClass, sort);
    }

    @Override
    public Optional<T> findById(ID id) {
        Specification<T> spec = (root, query, builder) -> builder.equal(root.get(entityInformation.getIdAttribute()), id);
        return findOne(spec);
    }

}
