package com.github.dac4j.demo.spring.data.jpa.dao.repository;

import com.github.dac4j.demo.spring.data.jpa.domain.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

}
