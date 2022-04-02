package com.github.fahdarhalai.demodac.dao.repository;

import com.github.fahdarhalai.demodac.domain.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

}
