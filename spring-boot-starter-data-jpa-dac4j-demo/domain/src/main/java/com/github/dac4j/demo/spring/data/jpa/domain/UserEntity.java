package com.github.dac4j.demo.spring.data.jpa.domain;

import com.github.dac4j.demo.spring.data.jpa.domain.common.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity extends AbstractEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String username;

}
