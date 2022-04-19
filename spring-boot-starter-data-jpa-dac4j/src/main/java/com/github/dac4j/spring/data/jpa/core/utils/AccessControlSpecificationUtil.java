package com.github.dac4j.spring.data.jpa.core.utils;

import com.github.dac4j.spring.data.jpa.core.annotation.AccessControl;
import com.github.dac4j.spring.data.jpa.core.extractor.SourceValueExtractor;
import com.github.dac4j.spring.data.jpa.domain.DataAccessControlEntity;
import com.github.dac4j.spring.data.jpa.domain.DataAccessControlEntity_;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Access control specification utility for building JPA specifications.
 *
 * @author Fahd Arhalai
 */
@SuppressWarnings("rawtypes")
public final class AccessControlSpecificationUtil {

    private static final String DEFAULT_ID_NAME = "id";

    private AccessControlSpecificationUtil() {
        throw new IllegalStateException("Cannot create instance of utility class.");
    }

    /**
     * Retrieves the complete JPA specification for access control verification. It checks for
     * possible {@link DataAccessControlEntity} records mentioning the main entity annotated
     * with {@link AccessControl}, and recursively applies the same verification over its parent
     * entities if the fields are annotated with {@link AccessControl.Parent} and the classes have
     * the {@link AccessControl} annotation similarly.
     * @param em EntityManager
     * @param domainType The controlled entity type
     * @param <T> designates the controlled entity type
     * @return JPA specification
     */
    public static <T> Specification<T> getAccessControlSpecification(EntityManager em,
                                                                     Class<T> domainType) {
        Specification<T> acSpec = (root, query, builder) ->
                getMainAccessControlPredicate(root, query, builder, em, domainType);
        Specification<T> acParentSpec = (root, query, builder) ->
                getParentAccessControlPredicate(domainType, null, root, query, builder, em, domainType);
        return acSpec.or(acParentSpec);
    }

    public static <T> Predicate getParentAccessControlPredicate(Class<?> clazz,
                                                                Path<?> fieldAccessor,
                                                                Root<T> root,
                                                                CriteriaQuery<?> query,
                                                                CriteriaBuilder builder,
                                                                EntityManager em,
                                                                Class<T> domainType) {
        List<Predicate> predicates = new ArrayList<>();
        for(Field field : clazz.getDeclaredFields()) {
            if(field.isAnnotationPresent(AccessControl.Parent.class)
                    && field.getType().isAnnotationPresent(AccessControl.class)) {
                predicates.add(getAccessControlPredicate(field, fieldAccessor, root, query, builder, em, domainType));
                predicates.add(getParentAccessControlPredicate(
                        field.getType(),
                        fieldAccessor == null ? root.get(field.getName()) : fieldAccessor.get(field.getName()),
                        root,
                        query,
                        builder,
                        em,
                        domainType)
                );
            }
        }

        return builder.or(predicates.toArray(new Predicate[]{}));
    }

    public static <T> Predicate getMainAccessControlPredicate(Root<T> root,
                                                              CriteriaQuery<?> query,
                                                              CriteriaBuilder builder,
                                                              EntityManager em,
                                                              Class<T> domainType) {
        return getAccessControlPredicate(null, null, root, query, builder, em, domainType);
    }

    private static <T> Predicate getAccessControlPredicate(Field field,
                                                          Path<?> fieldAccessor,
                                                          Root<T> root,
                                                          CriteriaQuery<?> query,
                                                          CriteriaBuilder builder,
                                                          EntityManager em,
                                                          Class<T> domainType) {

        // initialize entity metadata
        AccessControlSubQueryMetadata acqm = init(field, fieldAccessor, domainType, root, em);

        // create subquery
        Subquery<DataAccessControlEntity> subquery = query.subquery(DataAccessControlEntity.class);
        Root<DataAccessControlEntity> subRoot = subquery.from(DataAccessControlEntity.class);
        subquery.select(subRoot);

        // source predicates
        Predicate sourceTableNamePredicate = builder.equal(
                subRoot.get(DataAccessControlEntity_.sourceTableName),
                builder.literal(acqm.getSourceTableName()));

        Predicate sourceColumnNamePredicate = builder.equal(
                subRoot.get(DataAccessControlEntity_.sourceColumnName),
                builder.literal(JpaUtil.getColumnName(acqm.getSourceEntityInformation().getIdAttribute(), DEFAULT_ID_NAME, em)));

        Predicate sourceValuePredicate = builder.equal(
                subRoot.get(DataAccessControlEntity_.sourceValue),
                builder.literal(SourceValueExtractor.get().getSourceValue()));

        // target predicates
        Predicate targetTableNamePredicate = builder.equal(
                subRoot.get(DataAccessControlEntity_.targetTableName),
                builder.literal(acqm.getTargetTableName()));

        Predicate targetColumnNamePredicate = builder.equal(
                subRoot.get(DataAccessControlEntity_.targetColumnName),
                builder.literal(JpaUtil.getColumnName(acqm.getTargetEntityInformation().getIdAttribute(), DEFAULT_ID_NAME, em)));

        Predicate targetValuePredicate = builder.equal(
                subRoot.get(DataAccessControlEntity_.targetValue),
                acqm.getTargetPath().get(acqm.getTargetEntityInformation().getIdAttribute()));

        // predicates conjunction
        Predicate predicate = builder.and(
                sourceTableNamePredicate,
                sourceColumnNamePredicate,
                sourceValuePredicate,
                targetTableNamePredicate,
                targetColumnNamePredicate,
                targetValuePredicate);

        return builder.exists(subquery.where(predicate));
    }

    private static <T> AccessControlSubQueryMetadata init(Field field, Path<?> fieldAccessor, Class<T> domainType, Root<T> root, EntityManager em) {
        Path<?> targetPath;
        Class<?> targetEntity;
        if(field == null) {
            targetEntity = domainType;
            targetPath = root;
        } else {
            targetEntity = field.getType();
            targetPath = fieldAccessor == null ? root.get(field.getName()) : fieldAccessor.get(field.getName());
        }

        AccessControl accessControl = targetEntity.getAnnotation(AccessControl.class);
        JpaEntityInformation sourceEntityInformation = JpaEntityInformationSupport.getEntityInformation(accessControl.from(), em);
        String sourceTableName = JpaUtil.getTableName(accessControl.from(), em);

        JpaEntityInformation targetEntityInformation = JpaEntityInformationSupport.getEntityInformation(targetEntity, em);
        String targetTableName = JpaUtil.getTableName(targetEntity, em);

        return AccessControlSubQueryMetadata.builder()
                .accessControl(accessControl)
                .targetPath(targetPath)
                .targetEntity(targetEntity)
                .targetEntityInformation(targetEntityInformation)
                .targetTableName(targetTableName)
                .sourceEntityInformation(sourceEntityInformation)
                .sourceTableName(sourceTableName)
                .build();
    }

    @Getter
    @Builder
    public static class AccessControlSubQueryMetadata {

        /**
         * AccessControl annotation instance
         */
        private AccessControl accessControl;

        /**
         * Path to the entity being controlled
         */
        private Path<?> targetPath;

        /**
         * Entity being controlled
         */
        private Class<?> targetEntity;

        /**
         * JpaEntityInformation of the controlled entity
         */
        private JpaEntityInformation targetEntityInformation;

        /**
         * Actual table name of the controlled entity
         */
        private String targetTableName;

        /**
         * JpaEntityInformation of the entity wishing to access the controlled entity
         */
        private JpaEntityInformation sourceEntityInformation;

        /**
         * Actual table name of the entity wishing to access the controlled entity
         */
        private String sourceTableName;
    }
}
