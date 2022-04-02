package com.github.fahdarhalai.demodac.dao;

import com.github.fahdarhalai.demodac.dao.repository.CityRepository;
import com.github.fahdarhalai.demodac.dao.common.AbstractDao;
import com.github.fahdarhalai.demodac.domain.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CityDao extends AbstractDao<CityEntity, Long> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public JpaRepository<CityEntity, Long> getJpaRepository() {
        return cityRepository;
    }

}
