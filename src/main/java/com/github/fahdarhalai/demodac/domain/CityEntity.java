package com.github.fahdarhalai.demodac.domain;

import com.github.fahdarhalai.dac.annotation.Partition;
import com.github.fahdarhalai.demodac.domain.common.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Partition(sourceTable = "user_entity", sourceColumn = "id", targetTable = "city_entity", targetColumn = "id")
public class CityEntity extends AbstractEntity {

    @Column
    private String name;

    @OneToOne(mappedBy = "city")
    private StatisticsEntity statistics;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private CountryEntity country;
}
