package com.ebatuhan.todo.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebatuhan.todo.exception.ResourceNotFoundException;
import com.ebatuhan.todo.repository.TodoRepository;
import com.ebatuhan.todo.service.IAuthorizationService;


@Service("authorizationService")
public class AuthorizationServiceImpl implements IAuthorizationService {

    private final TodoRepository todoRepository;

    public AuthorizationServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public boolean isOwnerOfTodo(Long id) {

        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("todo with id " + id + " not found"))
                .getTodoUser().getUsername()
                .equals(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
