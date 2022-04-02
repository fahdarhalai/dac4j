package com.github.fahdarhalai.demodac.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CountryDto {

    private Long id;

    private String name;

    private List<CityDto> cities;

}
