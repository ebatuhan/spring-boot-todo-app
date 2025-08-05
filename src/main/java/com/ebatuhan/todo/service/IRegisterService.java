package com.ebatuhan.todo.service;

import com.ebatuhan.todo.dto.RegisterRequestDto;
import com.ebatuhan.todo.dto.RegisterResponseDto;

public interface IRegisterService {
	RegisterResponseDto createNewUser(RegisterRequestDto registerRequestDto);
}
