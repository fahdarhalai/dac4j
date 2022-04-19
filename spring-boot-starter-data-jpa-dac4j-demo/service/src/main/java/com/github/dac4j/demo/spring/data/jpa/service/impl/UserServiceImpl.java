package com.github.dac4j.demo.spring.data.jpa.service.impl;

import com.github.dac4j.demo.spring.data.jpa.domain.UserEntity;
import com.github.dac4j.demo.spring.data.jpa.service.dto.UserDto;
import com.github.dac4j.demo.spring.data.jpa.dao.UserDao;
import com.github.dac4j.demo.spring.data.jpa.service.UserService;
import com.github.dac4j.demo.spring.data.jpa.service.exception.DataNotFoundException;
import com.github.dac4j.demo.spring.data.jpa.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private UserMapper mapper;

    @Override
    @Transactional
    public List<UserDto> findAll() {
        return mapper.toDto(dao.findAll());
    }

    @Override
    @Transactional
    public UserDto findOne(Long aLong) {
        return dao.findById(aLong).map(mapper::toDto).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public UserDto findByUsername(String username) {
        Optional<UserEntity> en = dao.findByUsername(username);
        return en.map(mapper::toDto).orElseThrow(DataNotFoundException::new);
    }
}
