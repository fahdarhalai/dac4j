package com.github.dac4j.demo.spring.data.jpa.dao;

import com.github.dac4j.demo.spring.data.jpa.dao.repository.UserRepository;
import com.github.dac4j.demo.spring.data.jpa.dao.common.AbstractDao;
import com.github.dac4j.demo.spring.data.jpa.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao extends AbstractDao<UserEntity, UserRepository, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRepository getJpaRepository() {
        return userRepository;
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
