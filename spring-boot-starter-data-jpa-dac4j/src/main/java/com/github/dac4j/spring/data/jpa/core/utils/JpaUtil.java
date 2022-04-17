package com.github.dac4j.spring.data.jpa.core.utils;

import org.hibernate.Session;
import org.hibernate.boot.model.naming.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.selector.spi.StrategySelector;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.jdbc.spi.JdbcServices;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Optional;

@SuppressWarnings("rawtypes")
public class JpaUtil {

    private JpaUtil() {
        throw new IllegalStateException("Cannot create instance of utility class.");
    }

    public static PhysicalNamingStrategy physicalNamingStrategy(EntityManager em) {
        StandardServiceRegistry registry = em.unwrap(Session.class)
                .getSessionFactory()
                .getSessionFactoryOptions()
                .getServiceRegistry();

        return registry.getService(StrategySelector.class)
                .resolveDefaultableStrategy(
                        PhysicalNamingStrategy.class,
                        registry.getService(ConfigurationService.class)
                                .getSettings()
                                .get( AvailableSettings.PHYSICAL_NAMING_STRATEGY ),
                        PhysicalNamingStrategyStandardImpl.INSTANCE
                );
    }

    public static ImplicitNamingStrategy implicitNamingStrategy(EntityManager em) {
        StandardServiceRegistry registry = em.unwrap(Session.class)
                .getSessionFactory()
                .getSessionFactoryOptions()
                .getServiceRegistry();

        return registry.getService(StrategySelector.class)
                .resolveDefaultableStrategy(
                        ImplicitNamingStrategy.class,
                        registry.getService(ConfigurationService.class)
                                .getSettings()
                                .get( AvailableSettings.IMPLICIT_NAMING_STRATEGY ),
                        ImplicitNamingStrategyJpaCompliantImpl.INSTANCE
                );
    }

    public static String getTableName(Class<?> clazz, EntityManager em) {
        if(clazz.isAnnotationPresent(Table.class)) {
            return clazz.getAnnotation(Table.class).name();
        }

        JdbcEnvironment jdbcEnvironment = em.unwrap(Session.class)
                .getSessionFactory()
                .getSessionFactoryOptions()
                .getServiceRegistry()
                .getService(JdbcServices.class)
                .getJdbcEnvironment();

        return physicalNamingStrategy(em)
                .toPhysicalTableName(Identifier.toIdentifier(clazz.getSimpleName()), jdbcEnvironment)
                .render();
    }

    public static String getColumnName(String fieldName, EntityManager em) {
        JdbcEnvironment jdbcEnvironment = em.unwrap(Session.class)
                .getSessionFactory()
                .getSessionFactoryOptions()
                .getServiceRegistry()
                .getService(JdbcServices.class)
                .getJdbcEnvironment();

        return physicalNamingStrategy(em)
                .toPhysicalColumnName(Identifier.toIdentifier(fieldName), jdbcEnvironment)
                .render();
    }

    public static String getColumnName(SingularAttribute<?,?> singularAttribute, String defaultColumnName, EntityManager em) {
        return singularAttribute == null
                ? defaultColumnName
                : Optional.of(getColumnName(singularAttribute.getName(), em)).orElse(defaultColumnName);
    }
}
