package com.github.dac4j.demo.spring.data.jpa.api;

import com.github.dac4j.demo.spring.data.jpa.service.StatisticsService;
import com.github.dac4j.demo.spring.data.jpa.service.dto.StatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsResource {

    @Autowired
    private StatisticsService service;

    @GetMapping
    public ResponseEntity<List<StatisticsDto>> fetchAllStatistics() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatisticsDto> fetchStatistics(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<StatisticsDto> fetchStatisticsByCity(@PathVariable(value = "cityId") Long cityId) {
        return ResponseEntity.ok(service.findByCity(cityId));
    }
}
