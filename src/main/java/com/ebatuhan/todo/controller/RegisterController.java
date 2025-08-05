package com.ebatuhan.todo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebatuhan.todo.dto.RegisterRequestDto;
import com.ebatuhan.todo.dto.RegisterResponseDto;
import com.ebatuhan.todo.exception.BadPasswordPatternException;
import com.ebatuhan.todo.service.IRegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final IRegisterService registerService;

    public RegisterController(IRegisterService registerService) {
        this.registerService = registerService;

    }

    @PostMapping
    public RegisterResponseDto createNewUser(@RequestBody RegisterRequestDto registerRequestDto) {
        final String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=\\S+$).{8,}$";

        if (registerRequestDto.getPassword().matches(pattern)) {
            return registerService.createNewUser(registerRequestDto);
        }

        throw new BadPasswordPatternException("Weak password.");

    }
}
