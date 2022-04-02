package com.github.fahdarhalai.demodac.domain;

import com.github.fahdarhalai.demodac.domain.common.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StatisticsEntity extends AbstractEntity {

    @Column
    private Long population;

    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private CityEntity city;
}
