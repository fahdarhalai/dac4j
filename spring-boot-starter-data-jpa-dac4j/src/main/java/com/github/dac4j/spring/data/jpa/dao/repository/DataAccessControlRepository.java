package com.github.dac4j.spring.data.jpa.dao.repository;

import com.github.dac4j.spring.data.jpa.domain.DataAccessControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataAccessControlRepository extends JpaRepository<DataAccessControlEntity, Long> {

    List<DataAccessControlEntity> findBySourceTableNameAndSourceColumnNameAndTargetTableNameAndTargetColumnName(
            String sourceTableName,
            String sourceColumnName,
            String targetTableName,
            String targetColumnName);
}