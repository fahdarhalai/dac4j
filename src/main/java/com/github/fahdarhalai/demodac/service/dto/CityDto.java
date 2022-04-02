package com.github.fahdarhalai.demodac.service.dto;

import lombok.Data;

@Data
public class CityDto {

    private Long id;

    private String name;

    private StatisticsDto statistics;

    private Long countryId;

}
