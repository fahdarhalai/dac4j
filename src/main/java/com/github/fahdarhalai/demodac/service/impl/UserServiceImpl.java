package com.github.fahdarhalai.demodac.service.impl;

import com.github.fahdarhalai.demodac.dao.UserDao;
import com.github.fahdarhalai.demodac.service.UserService;
import com.github.fahdarhalai.demodac.service.dto.UserDto;
import com.github.fahdarhalai.demodac.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserDto> findAll() {
        return mapper.toDto(dao.findAll());
    }

    @Override
    public Optional<UserDto> findOne(Long aLong) {
        return dao.findById(aLong).map(mapper::toDto);
    }
}
