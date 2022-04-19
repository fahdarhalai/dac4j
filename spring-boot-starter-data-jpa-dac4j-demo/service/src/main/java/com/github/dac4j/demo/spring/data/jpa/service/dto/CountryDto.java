package com.github.dac4j.demo.spring.data.jpa.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CountryDto {

    private Long id;

    private String name;

    private List<CityDto> cities;

}
