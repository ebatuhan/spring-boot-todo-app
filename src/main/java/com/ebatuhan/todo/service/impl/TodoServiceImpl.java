package com.ebatuhan.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.ebatuhan.todo.dto.ToDoRequestDto;
import com.ebatuhan.todo.dto.TodoResponseDto;
import com.ebatuhan.todo.exception.ResourceNotFoundException;
import com.ebatuhan.todo.model.Todo;
import com.ebatuhan.todo.model.TodoUser;
import com.ebatuhan.todo.repository.TodoRepository;
import com.ebatuhan.todo.repository.TodoUserRepository;
import com.ebatuhan.todo.service.ITodoService;

@Service
public class TodoServiceImpl implements ITodoService {

	private final TodoRepository toDoRepository;
	private final TodoUserRepository todoUserRepository;
	private final ModelMapper modelMapper;

	public TodoServiceImpl(TodoRepository toDoRepository, ModelMapper modelMapper,
			TodoUserRepository todoUserRepository) {
		this.toDoRepository = toDoRepository;
		this.todoUserRepository = todoUserRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	@PostAuthorize("@authorizationService.isOwnerOfTodo(#id)")
	public TodoResponseDto findById(Long id) {
		Todo todo = toDoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo with id : " + id + "not found"));

		return modelMapper.map(todo, TodoResponseDto.class);
	}

	@Override
	public List<TodoResponseDto> findAll() {

		final TodoUser todoUser = todoUserRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
				.get();

		return toDoRepository.findAllByTodoUser_Id(todoUser.getId())
				.stream()
				.map(todo -> modelMapper.map(todo, TodoResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoResponseDto createToDo(ToDoRequestDto toDoRequestDto) {

		Todo todo = modelMapper.map(toDoRequestDto, Todo.class);

		todo.setTodoUser(
				todoUserRepository.findByUsername(
						SecurityContextHolder.getContext()
								.getAuthentication()
								.getName())
						.get());

		Todo savedTodo = toDoRepository.save(todo);

		return modelMapper.map(savedTodo, TodoResponseDto.class);

	}

	@Override
	@Transactional
	@PreAuthorize("@authorizationService.isOwnerOfTodo(#id)")
	public boolean deleteById(Long id) {

		boolean isExists = toDoRepository.existsById(id);

		if (isExists) {
			toDoRepository.deleteById(id);
			return true;
		}

		throw new ResourceNotFoundException("Todo with id: " + id + " not exists.");

	}

	@Override
	@PreAuthorize("@authorizationService.isOwnerOfTodo(#id)")
	public TodoResponseDto updateToDo(Long id, ToDoRequestDto toDoRequestDto) {
		Todo targetTodo = toDoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo with id " + id + " not found."));

		modelMapper.map(toDoRequestDto, targetTodo);

		Todo changedTodo = toDoRepository.save(targetTodo);

		return modelMapper.map(changedTodo, TodoResponseDto.class);
	}

}
