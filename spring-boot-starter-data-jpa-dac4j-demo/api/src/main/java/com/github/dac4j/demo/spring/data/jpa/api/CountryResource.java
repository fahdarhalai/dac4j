package com.github.dac4j.demo.spring.data.jpa.api;

import com.github.dac4j.demo.spring.data.jpa.service.CountryService;
import com.github.dac4j.demo.spring.data.jpa.service.dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryResource {

    @Autowired
    private CountryService service;

    @GetMapping
    public ResponseEntity<List<CountryDto>> fetchAllCountries() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> fetchCountry(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }
}
