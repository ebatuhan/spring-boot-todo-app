package com.ebatuhan.todo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebatuhan.todo.dto.ToDoRequestDto;
import com.ebatuhan.todo.dto.TodoResponseDto;
import com.ebatuhan.todo.service.ITodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {

	private final ITodoService todoService;

	public TodoController(ITodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/{id}")
	private TodoResponseDto findById(@PathVariable Long id) {
		return todoService.findById(id);
	}

	@GetMapping
	private List<TodoResponseDto> findAll() {
		return todoService.findAll();
	}

	@PostMapping
	private TodoResponseDto createToDo(@RequestBody ToDoRequestDto toDoRequestDto) {
		return todoService.createToDo(toDoRequestDto);
	}

	@DeleteMapping("/{id}")
	private boolean deleteById(@PathVariable Long id) {
		return todoService.deleteById(id);
	}

	@PutMapping("/{id}")
	private TodoResponseDto updateToDo(@PathVariable Long id, @RequestBody ToDoRequestDto toDoRequestDto) {
		return todoService.updateToDo(id, toDoRequestDto);
	}

}
