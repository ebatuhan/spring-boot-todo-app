package com.ebatuhan.todo.service;

public interface IAuthorizationService {
    boolean isOwnerOfTodo(Long id);
}
