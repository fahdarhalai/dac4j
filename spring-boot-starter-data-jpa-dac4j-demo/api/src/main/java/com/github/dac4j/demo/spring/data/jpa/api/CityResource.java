package com.github.dac4j.demo.spring.data.jpa.api;

import com.github.dac4j.demo.spring.data.jpa.service.CityService;
import com.github.dac4j.demo.spring.data.jpa.service.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityResource {

    @Autowired
    private CityService service;

    @GetMapping
    public ResponseEntity<List<CityDto>> fetchCities() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> fetchCity(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }
}
