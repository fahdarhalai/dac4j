package com.github.dac4j.spring.data.jpa.service;

import com.github.dac4j.spring.data.jpa.service.dto.DataAccessControlDto;

import java.util.List;

/**
 * Data access control service interface.
 *
 * @author Fahd Arhalai
 */
public interface DataAccessControlService {

    /**
     * Lists all data access control mappings
     * @return list of mappings
     */
    List<DataAccessControlDto> findAll();

    /**
     * Retrieves a data access control mapping by id
     * @param id identifier of the mapping
     * @return mapping
     */
    DataAccessControlDto findById(Long id);

    /**
     * Creates a new data access control mapping
     * @param dataAccessControlDto mapping dto
     * @return mapping identifier
     */
    Long create(DataAccessControlDto dataAccessControlDto);

    /**
     * Updates data access control mapping
     * @param id identifier of the mapping
     * @param dataAccessControlDto updated mapping data
     * @return data mapping
     */
    DataAccessControlDto update(Long id, DataAccessControlDto dataAccessControlDto);

    /**
     * Deletes data access control mapping by id
     * @param id identifier of the mapping
     */
    void deleteById(Long id);
}
