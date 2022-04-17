package com.github.dac4j.spring.data.jpa.domain;

import lombok.*;

import javax.persistence.*;

/**
 * The main access control persistence entity. It binds source table records to the target
 * table records upon which the former have visibility, and thus can perform DML queries.
 */
@Entity
@Table(name = "DATA_ACCESS_CONTROL")
@Getter
@Setter
public class DataAccessControlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Resource that should be controlled before performing read/write queries.
     */
    private String targetTableName;

    /**
     * Resource through which the access to the target resource should be verified.
     */
    private String sourceTableName;

    /**
     * Column name in the target resource.
     */
    private String targetColumnName;

    /**
     * Column name in the source resource.
     */
    private String sourceColumnName;

    /**
     * Target value.
     */
    private String targetValue;

    /**
     * Source value.
     */
    private String sourceValue;

}