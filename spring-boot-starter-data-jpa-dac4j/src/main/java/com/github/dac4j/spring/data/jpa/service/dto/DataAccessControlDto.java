package com.github.dac4j.spring.data.jpa.service.dto;

import lombok.Data;

/**
 * Data access control DTO object.
 *
 * @author Fahd Arhalai
 */
@Data
public class DataAccessControlDto {

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
