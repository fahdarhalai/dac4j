package com.github.dac4j.spring.data.jpa.core.factory;

import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.projection.CollectionAwareProjectionFactory;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.*;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.jpa.util.JpaMetamodel;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.QueryCreationListener;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.SurroundingTransactionDetectorMethodInterceptor;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.data.repository.query.ReturnedType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT;

/**
 * JPA specific access control repository factory.
 *
 * @author Fahd Arhalai
 */

public class AccessControlJpaRepositoryFactory extends RepositoryFactorySupport {

    private final EntityManager entityManager;
    private final QueryExtractor extractor;
    private final AccessControlCrudMethodMetadataPostProcessor crudMethodMetadataPostProcessor;

    private EntityPathResolver entityPathResolver;
    private EscapeCharacter escapeCharacter = EscapeCharacter.DEFAULT;
    private JpaQueryMethodFactory queryMethodFactory;

    /**
     * Creates a new {@link JpaRepositoryFactory}.
     *
     * @param entityManager must not be {@literal null}
     */
    public AccessControlJpaRepositoryFactory(EntityManager entityManager) {

        Assert.notNull(entityManager, "EntityManager must not be null!");

        this.entityManager = entityManager;
        this.extractor = PersistenceProvider.fromEntityManager(entityManager);
        this.crudMethodMetadataPostProcessor = new AccessControlCrudMethodMetadataPostProcessor();
        this.entityPathResolver = SimpleEntityPathResolver.INSTANCE;
        this.queryMethodFactory = new AccessControlJpaQueryMethodFactory(extractor);

        addRepositoryProxyPostProcessor(crudMethodMetadataPostProcessor);
        addRepositoryProxyPostProcessor((factory, repositoryInformation) -> {

            if (isTransactionNeeded(repositoryInformation.getRepositoryInterface())) {
                factory.addAdvice(SurroundingTransactionDetectorMethodInterceptor.INSTANCE);
            }
        });

        if (extractor.equals(PersistenceProvider.ECLIPSELINK)) {
            addQueryCreationListener(new EclipseLinkProjectionQueryCreationListener(entityManager));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#setBeanClassLoader(java.lang.ClassLoader)
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

        super.setBeanClassLoader(classLoader);
        this.crudMethodMetadataPostProcessor.setBeanClassLoader(classLoader);
    }

    /**
     * Configures the {@link EntityPathResolver} to be used. Defaults to {@link SimpleEntityPathResolver#INSTANCE}.
     *
     * @param entityPathResolver must not be {@literal null}.
     */
    public void setEntityPathResolver(EntityPathResolver entityPathResolver) {

        Assert.notNull(entityPathResolver, "EntityPathResolver must not be null!");

        this.entityPathResolver = entityPathResolver;
    }

    /**
     * Configures the escape character to be used for like-expressions created for derived queries.
     *
     * @param escapeCharacter a character used for escaping in certain like expressions.
     */
    public void setEscapeCharacter(EscapeCharacter escapeCharacter) {
        this.escapeCharacter = escapeCharacter;
    }

    /**
     * Configures the {@link JpaQueryMethodFactory} to be used. Defaults to {@link DefaultJpaQueryMethodFactory}.
     *
     * @param queryMethodFactory must not be {@literal null}.
     */
    public void setQueryMethodFactory(JpaQueryMethodFactory queryMethodFactory) {

        Assert.notNull(queryMethodFactory, "QueryMethodFactory must not be null!");

        this.queryMethodFactory = queryMethodFactory;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getTargetRepository(org.springframework.data.repository.core.RepositoryMetadata)
     */
    @Override
    protected final JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information) {

        JpaRepositoryImplementation<?, ?> repository = getTargetRepository(information, entityManager);
        repository.setRepositoryMethodMetadata(crudMethodMetadataPostProcessor.getCrudMethodMetadata());
        repository.setEscapeCharacter(escapeCharacter);

        return repository;
    }

    /**
     * Callback to create a {@link JpaRepository} instance with the given {@link EntityManager}
     *
     * @param information will never be {@literal null}.
     * @param entityManager will never be {@literal null}.
     * @return
     */
    protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information,
                                                                    EntityManager entityManager) {

        JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
        Object repository = getTargetRepositoryViaReflection(information, entityInformation, entityManager);

        Assert.isInstanceOf(JpaRepositoryImplementation.class, repository);

        return (JpaRepositoryImplementation<?, ?>) repository;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getRepositoryBaseClass(org.springframework.data.repository.core.RepositoryMetadata)
     */
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return SimpleJpaRepository.class;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getProjectionFactory(java.lang.ClassLoader, org.springframework.beans.factory.BeanFactory)
     */
    @Override
    protected ProjectionFactory getProjectionFactory(ClassLoader classLoader, BeanFactory beanFactory) {

        CollectionAwareProjectionFactory factory = new CollectionAwareProjectionFactory();
        factory.setBeanClassLoader(classLoader);
        factory.setBeanFactory(beanFactory);

        return factory;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getQueryLookupStrategy(org.springframework.data.repository.query.QueryLookupStrategy.Key, org.springframework.data.repository.query.EvaluationContextProvider)
     */
    @Override
    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(@Nullable QueryLookupStrategy.Key key,
                                                                   QueryMethodEvaluationContextProvider evaluationContextProvider) {

        return Optional.of(JpaQueryLookupStrategy.create(entityManager, queryMethodFactory, key, evaluationContextProvider,
                escapeCharacter));
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getEntityInformation(java.lang.Class)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T, ID> JpaEntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {

        return (JpaEntityInformation<T, ID>) JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getRepositoryFragments(org.springframework.data.repository.core.RepositoryMetadata)
     */
    @Override
    protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {

        return getRepositoryFragments(metadata, entityManager, entityPathResolver,
                crudMethodMetadataPostProcessor.getCrudMethodMetadata());
    }

    /**
     * Creates {@link RepositoryComposition.RepositoryFragments} based on {@link RepositoryMetadata} to add JPA-specific extensions. Typically
     * adds a {@link QuerydslJpaPredicateExecutor} if the repository interface uses Querydsl.
     * <p>
     * Can be overridden by subclasses to customize {@link RepositoryComposition.RepositoryFragments}.
     *
     * @param metadata repository metadata.
     * @param entityManager the entity manager.
     * @param resolver resolver to translate an plain domain class into a {@link com.querydsl.core.types.EntityPath}.
     * @param crudMethodMetadata metadata about the invoked CRUD methods.
     * @return
     * @since 2.5.1
     */
    protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata, EntityManager entityManager,
                                                                               EntityPathResolver resolver, CrudMethodMetadata crudMethodMetadata) {

        boolean isQueryDslRepository = QUERY_DSL_PRESENT
                && QuerydslPredicateExecutor.class.isAssignableFrom(metadata.getRepositoryInterface());

        if (isQueryDslRepository) {

            if (metadata.isReactiveRepository()) {
                throw new InvalidDataAccessApiUsageException(
                        "Cannot combine Querydsl and reactive repository support in a single interface");
            }

            return RepositoryComposition.RepositoryFragments.just(new QuerydslJpaPredicateExecutor<>(getEntityInformation(metadata.getDomainType()),
                    entityManager, resolver, crudMethodMetadata));
        }

        return RepositoryComposition.RepositoryFragments.empty();
    }

    private static boolean isTransactionNeeded(Class<?> repositoryClass) {

        Method[] methods = ReflectionUtils.getAllDeclaredMethods(repositoryClass);

        for (Method method : methods) {
            if (Stream.class.isAssignableFrom(method.getReturnType()) || method.isAnnotationPresent(Procedure.class)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Query creation listener that informs EclipseLink users that they have to be extra careful when defining repository
     * query methods using projections as we have to rely on the declaration order of the accessors in projection
     * interfaces matching the order in columns. Alias-based mapping doesn't work with EclipseLink as it doesn't support
     * {@link Tuple} based queries yet.
     *
     * @author Oliver Gierke
     * @since 2.0.5
     * @see <a href=
     *      "https://bugs.eclipse.org/bugs/show_bug.cgi?id=289141">https://bugs.eclipse.org/bugs/show_bug.cgi?id=289141</a>
     */
    private static class EclipseLinkProjectionQueryCreationListener implements QueryCreationListener<AbstractJpaQuery> {

        private static final String ECLIPSELINK_PROJECTIONS = "Usage of Spring Data projections detected on persistence provider EclipseLink. Make sure the following query methods declare result columns in exactly the order the accessors are declared in the projecting interface or the order of parameters for DTOs:";

        private static final Logger log = org.slf4j.LoggerFactory
                .getLogger(EclipseLinkProjectionQueryCreationListener.class);

        private final JpaMetamodel metamodel;

        private boolean warningLogged = false;

        /**
         * Creates a new {@link EclipseLinkProjectionQueryCreationListener} for the given {@link EntityManager}.
         *
         * @param em must not be {@literal null}.
         */
        public EclipseLinkProjectionQueryCreationListener(EntityManager em) {

            Assert.notNull(em, "EntityManager must not be null!");

            this.metamodel = JpaMetamodel.of(em.getMetamodel());
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.repository.core.support.QueryCreationListener#onCreation(org.springframework.data.repository.query.RepositoryQuery)
         */
        @Override
        public void onCreation(AbstractJpaQuery query) {

            JpaQueryMethod queryMethod = query.getQueryMethod();
            ReturnedType type = queryMethod.getResultProcessor().getReturnedType();

            if (type.isProjecting() && !metamodel.isJpaManaged(type.getReturnedType())) {

                if (!warningLogged) {
                    log.info(ECLIPSELINK_PROJECTIONS);
                    this.warningLogged = true;
                }

                log.info(" - {}", queryMethod);
            }
        }
    }
}
