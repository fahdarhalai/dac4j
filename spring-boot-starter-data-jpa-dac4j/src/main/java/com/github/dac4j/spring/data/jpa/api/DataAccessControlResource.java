package com.github.dac4j.spring.data.jpa.api;

import com.github.dac4j.spring.data.jpa.service.DataAccessControlService;
import com.github.dac4j.spring.data.jpa.service.dto.DataAccessControlDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data-access-control")
public class DataAccessControlResource {

    private final DataAccessControlService service;

    public DataAccessControlResource(DataAccessControlService dataAccessControlService) {
        this.service = dataAccessControlService;
    }

    @GetMapping
    public ResponseEntity<List<DataAccessControlDto>> getAllDac() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataAccessControlDto> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody DataAccessControlDto body) {
        return new ResponseEntity<>(
                service.create(body),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataAccessControlDto> update(@PathVariable(value = "id") Long id,
                                                       @RequestBody DataAccessControlDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
