package com.github.fahdarhalai.demodac.domain;

import com.github.fahdarhalai.dac.annotation.Partition;
import com.github.fahdarhalai.demodac.domain.common.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Partition(sourceTable = "user_entity", sourceColumn = "id", targetTable = "country_entity", targetColumn = "id")
public class CountryEntity extends AbstractEntity {

    @Column
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "country")
    private List<CityEntity> cities;
}
