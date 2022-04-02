package com.github.fahdarhalai.demodac.dao.repository;

import com.github.fahdarhalai.demodac.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
