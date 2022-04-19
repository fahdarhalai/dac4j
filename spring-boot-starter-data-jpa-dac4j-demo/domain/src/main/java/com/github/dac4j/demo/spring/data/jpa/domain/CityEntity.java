package com.github.dac4j.demo.spring.data.jpa.domain;

import com.github.dac4j.demo.spring.data.jpa.domain.common.AbstractEntity;
import com.github.dac4j.spring.data.jpa.core.annotation.AccessControl;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Getter
@Setter
@AccessControl(from = UserEntity.class)
public class CityEntity extends AbstractEntity {

    @Column
    private String name;

    @OneToOne(mappedBy = "city")
    private StatisticsEntity statistics;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    @AccessControl.Parent
    private CountryEntity country;


}
