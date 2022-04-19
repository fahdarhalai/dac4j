package com.github.dac4j.demo.spring.data.jpa.service;

import com.github.dac4j.demo.spring.data.jpa.service.common.AbstractService;
import com.github.dac4j.demo.spring.data.jpa.service.dto.UserDto;

public interface UserService extends AbstractService<UserDto, Long> {

    UserDto findByUsername(String username);
}
