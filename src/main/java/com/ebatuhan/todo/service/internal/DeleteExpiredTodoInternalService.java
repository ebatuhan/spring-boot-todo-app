package com.ebatuhan.todo.service.internal;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ebatuhan.todo.repository.TodoRepository;

@Service
public class DeleteExpiredTodoInternalService {

    private final TodoRepository todoRepository;

    public DeleteExpiredTodoInternalService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;

    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void deleteExpiredTodos() {
        todoRepository.deleteExpiredTodos();
    }

}
