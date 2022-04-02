package com.github.fahdarhalai.demodac.dao;

import com.github.fahdarhalai.demodac.dao.repository.CountryRepository;
import com.github.fahdarhalai.demodac.dao.common.AbstractDao;
import com.github.fahdarhalai.demodac.domain.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountryDao extends AbstractDao<CountryEntity, Long> {

    @Autowired
    private CountryRepository countryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public JpaRepository<CountryEntity, Long> getJpaRepository() {
        return countryRepository;
    }

    @Override
    public List<CountryEntity> findAll() {
        TypedQuery<CountryEntity> query = entityManager.createQuery(
                "SELECT c from CountryEntity c " +
                        "JOIN DataAccessControlEntity dac " +
                        "ON dac.sourceTableName = :sourceTableName " +
                        "AND dac.sourceColumnName = :sourceColumnName " +
                        "AND dac.targetTableName = :targetTableName " +
                        "AND dac.targetColumnName = :targetColumnName " +
                        "AND dac.sourceValue = :sourceValue " +
                        "AND dac.targetValue = CONCAT(c.id, '') ",
                CountryEntity.class);

        query.setParameter("sourceValue", getUserId().toString());
        query.setParameter("targetColumnName", "id");
        query.setParameter("targetTableName", "country_entity");
        query.setParameter("sourceColumnName", "id");
        query.setParameter("sourceTableName", "user_entity");

        return query.getResultList();
    }

    private Long getUserId() {
        return 1L;
    }
}
