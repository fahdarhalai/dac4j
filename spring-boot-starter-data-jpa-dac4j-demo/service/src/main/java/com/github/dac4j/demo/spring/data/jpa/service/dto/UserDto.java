package com.github.dac4j.demo.spring.data.jpa.service.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;
}
