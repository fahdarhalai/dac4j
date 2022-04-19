package com.github.dac4j.demo.spring.data.jpa.api;

import com.github.dac4j.demo.spring.data.jpa.service.UserService;
import com.github.dac4j.demo.spring.data.jpa.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> fetchAllUsers() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> fetchUser(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }
}
