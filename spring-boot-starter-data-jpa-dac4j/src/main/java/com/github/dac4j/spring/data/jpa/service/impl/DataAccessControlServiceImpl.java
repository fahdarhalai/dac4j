package com.github.dac4j.spring.data.jpa.service.impl;

import com.github.dac4j.spring.data.jpa.service.DataAccessControlService;
import com.github.dac4j.spring.data.jpa.service.dto.DataAccessControlDto;
import com.github.dac4j.spring.data.jpa.service.mapper.DataAccessControlMapper;
import com.github.dac4j.spring.data.jpa.dao.repository.DataAccessControlRepository;
import com.github.dac4j.spring.data.jpa.domain.DataAccessControlEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Data access control service implementation.
 *
 * @author Fahd Arhalai
 */
@Service
public class DataAccessControlServiceImpl implements DataAccessControlService {

    private final DataAccessControlMapper mapper;

    private final DataAccessControlRepository repository;

    public DataAccessControlServiceImpl(DataAccessControlMapper mapper,
                                        DataAccessControlRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<DataAccessControlDto> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    @Transactional
    public DataAccessControlDto findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public Long create(DataAccessControlDto dataAccessControlDto) {
        dataAccessControlDto.setId(null);
        DataAccessControlEntity dataAccessControlEntity = mapper.toEntity(dataAccessControlDto);
        dataAccessControlEntity = repository.save(dataAccessControlEntity);

        return dataAccessControlEntity.getId();
    }

    @Override
    @Transactional
    public DataAccessControlDto update(Long id, DataAccessControlDto dataAccessControlDto) {
        dataAccessControlDto.setId(id);

        DataAccessControlEntity dataAccessControlEntity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.updateEntityFromDto(dataAccessControlEntity, dataAccessControlDto);
        dataAccessControlEntity = repository.save(dataAccessControlEntity);

        return mapper.toDto(dataAccessControlEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
    }
}
