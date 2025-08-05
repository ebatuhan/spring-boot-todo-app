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
public class RegisterRequestDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Date birthDate;

}
