package com.github.dac4j.demo.spring.data.jpa.domain;

import com.github.dac4j.demo.spring.data.jpa.domain.common.AbstractEntity;
import com.github.dac4j.spring.data.jpa.core.annotation.AccessControl;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "country")
@Getter
@Setter
@AccessControl(from = UserEntity.class)
public class CountryEntity extends AbstractEntity {

    @Column
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "country")
    private List<CityEntity> cities;
}
