package com.ebatuhan.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebatuhan.todo.dto.JwtResponseDto;
import com.ebatuhan.todo.dto.LoginRequestDto;
import com.ebatuhan.todo.service.IAuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;

    }

    @PostMapping
    public JwtResponseDto authenticate(@RequestBody LoginRequestDto loginRequestDto) {

        return authenticationService.authenticate(loginRequestDto);

    }

}
