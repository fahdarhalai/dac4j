package com.github.fahdarhalai.demodac.dao;

import com.github.fahdarhalai.demodac.dao.common.AbstractDao;
import com.github.fahdarhalai.demodac.dao.repository.UserRepository;
import com.github.fahdarhalai.demodac.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<UserEntity, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public JpaRepository<UserEntity, Long> getJpaRepository() {
        return userRepository;
    }
}
