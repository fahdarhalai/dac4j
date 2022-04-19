package com.github.dac4j.spring.data.jpa.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation the binds a source entity with the one being annotated.
 * The annotated entity is also known as the target, which is the one being controlled.
 *
 * DAC4J uses {@link com.github.dac4j.spring.data.jpa.domain.DataAccessControlEntity} to
 * persist access control records.
 *
 * As for now, the source/target {@link javax.persistence.Id} annotated field is the primary
 * information needed to map a source with the target it has visibility on.
 *
 * The persistent access control records can span different targets, hence the need to
 * identify both the source and target tables.
 *
 * DAC4J verifies the existence of explicit table names through the {@link javax.persistence.Table}
 * annotation, otherwise it makes use of {@link org.hibernate.boot.model.naming.PhysicalNamingStrategy}
 * to deduce the implicit table name.
 *
 * @author Fahd Arhalai
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessControl {

    Class<?> from();

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Parent {}
}
