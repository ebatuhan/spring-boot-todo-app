package com.ebatuhan.todo.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Date birthDate;

}
