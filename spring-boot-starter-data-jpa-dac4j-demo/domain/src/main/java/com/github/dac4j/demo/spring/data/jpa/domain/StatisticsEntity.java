package com.github.dac4j.demo.spring.data.jpa.domain;

import com.github.dac4j.demo.spring.data.jpa.domain.common.AbstractEntity;
import com.github.dac4j.spring.data.jpa.core.annotation.AccessControl;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@AccessControl(from = UserEntity.class)
public class StatisticsEntity extends AbstractEntity {

    @Column
    private Long population;

    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    @AccessControl.Parent
    private CityEntity city;
}
