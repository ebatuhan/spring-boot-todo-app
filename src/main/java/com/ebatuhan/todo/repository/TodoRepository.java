package com.ebatuhan.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ebatuhan.todo.model.Todo;

import jakarta.transaction.Transactional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findAllByTodoUser_Id(Long userId);

	@Modifying
    @Transactional
	@Query(nativeQuery = true, value = "delete from todo where expire_date <= NOW()")
    void deleteExpiredTodos();

}
