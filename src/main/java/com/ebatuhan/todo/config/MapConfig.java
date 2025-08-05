package com.ebatuhan.todo.config;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ebatuhan.todo.dto.TodoResponseDto;
import com.ebatuhan.todo.model.Todo;
import com.ebatuhan.todo.model.TodoUser;

@Configuration
public class MapConfig {

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration()
				.setSkipNullEnabled(true);

		modelMapper.typeMap(Todo.class, TodoResponseDto.class).addMappings(mapper -> {
			mapper.map(src -> {
				TodoUser user = src.getTodoUser();
				return user != null ? user.getId() : null;
			}, TodoResponseDto::setTodoUserId);
		});

		return modelMapper;
	}

}
