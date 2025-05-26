package com.in28minutes.full_stack_13.rest_api_02.todo.repository;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	List<Todo> findByUsername(String username);

}