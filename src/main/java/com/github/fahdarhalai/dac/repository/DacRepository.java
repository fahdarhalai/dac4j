package com.github.fahdarhalai.dac.repository;

import com.github.fahdarhalai.dac.domain.DataAccessControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DacRepository extends JpaRepository<DataAccessControlEntity, Long> {

    List<DataAccessControlEntity> findBySourceTableNameAndSourceColumnNameAndTargetTableNameAndTargetColumnName(
            String sourceTableName,
            String sourceColumnName,
            String targetTableName,
            String targetColumnName);
}
