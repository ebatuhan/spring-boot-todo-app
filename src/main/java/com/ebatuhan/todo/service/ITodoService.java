package com.ebatuhan.todo.service;

import java.util.List;

import com.ebatuhan.todo.dto.ToDoRequestDto;
import com.ebatuhan.todo.dto.TodoResponseDto;

public interface ITodoService {

	TodoResponseDto findById(Long id);
	List<TodoResponseDto> findAll();
	TodoResponseDto createToDo(ToDoRequestDto toDoRequestDto);
	boolean deleteById(Long id);
	TodoResponseDto updateToDo(Long id, ToDoRequestDto toDoRequestDto);
}
