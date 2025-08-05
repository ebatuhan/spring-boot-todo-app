package com.ebatuhan.todo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebatuhan.todo.dto.RegisterRequestDto;
import com.ebatuhan.todo.dto.RegisterResponseDto;
import com.ebatuhan.todo.exception.UsernameTakenException;
import com.ebatuhan.todo.model.TodoUser;
import com.ebatuhan.todo.repository.TodoUserRepository;
import com.ebatuhan.todo.service.IRegisterService;

@Service
public class RegisterServiceImpl implements IRegisterService {

    private final TodoUserRepository todoUserRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(TodoUserRepository todoUserRepository, ModelMapper modelMapper,
            PasswordEncoder passwordEncoder) {
        this.todoUserRepository = todoUserRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponseDto createNewUser(RegisterRequestDto registerRequestDto) {

        String username = registerRequestDto.getUsername();

        boolean isExists = todoUserRepository.findByUsername(username).isPresent();

        if (isExists) {
            throw new UsernameTakenException(username + " is taken.");
        }

        TodoUser newUser = modelMapper.map(registerRequestDto, TodoUser.class);

        final String rawPassword = newUser.getPassword();
        final String cryptedPassword = passwordEncoder.encode(rawPassword);

        newUser.setPassword(cryptedPassword);

        TodoUser savedUser = todoUserRepository.save(newUser);

        return modelMapper.map(savedUser, RegisterResponseDto.class);
    }

}
