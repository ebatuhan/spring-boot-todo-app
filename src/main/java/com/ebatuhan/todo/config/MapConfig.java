package com.ebatuhan.todo.config;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ebatuhan.todo.dto.TodoResponseDto;
import com.ebatuhan.todo.model.Todo;

@Configuration
public class MapConfig {

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration()
				.setSkipNullEnabled(true);

modelMapper.typeMap(Todo.class, TodoResponseDto.class).addMappings(mapper -> {
    mapper.using(ctx -> {
        Object source = ctx.getSource();
        if (source == null) {
            System.out.println("[ModelMapper] todoUser is null");
            return null;
        }
        Long userId = ((com.ebatuhan.todo.model.TodoUser)((Todo) ctx.getParent().getSource()).getTodoUser()).getId();
        System.out.println("[ModelMapper] Mapping userId: " + userId);
        return userId;
    }).map(Todo::getTodoUser, TodoResponseDto::setTodoUserId);
});


		
		return modelMapper;
	}

}
