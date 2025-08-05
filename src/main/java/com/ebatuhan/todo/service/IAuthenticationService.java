package com.ebatuhan.todo.service;

import org.springframework.security.core.Authentication;

import com.ebatuhan.todo.dto.JwtResponseDto;
import com.ebatuhan.todo.dto.LoginRequestDto;

public interface IAuthenticationService {
    String generateJwtToken(Authentication authentication);

    JwtResponseDto authenticate(LoginRequestDto loginRequestDto);
}
