package com.ebatuhan.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebatuhan.todo.model.TodoUser;

public interface TodoUserRepository extends JpaRepository<TodoUser, Long> {
    Optional<TodoUser> findByUsername(String username);
}
