package com.in28minutes.full_stack_13.rest_api_02.todo;

@RestController
public class TodoJpaResource {
	
	private TodoService todoService;
	
	private TodoRepository todoRepository;
	
	public TodoJpaResource(TodoService todoService, TodoRepository todoRepository) {
		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		//return todoService.findByUsername(username);
		return todoRepository.findByUsername(username);
	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username,
			@PathVariable int id) {
		//return todoService.findById(id);
		return todoRepository.findById(id).get();
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,
			@PathVariable int id) {
		//todoService.deleteById(id);
		todoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username,
			@PathVariable int id, @RequestBody Todo todo) {
		//todoService.updateTodo(todo);
		todoRepository.save(todo);
		return todo;
	}

	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username,
			 @RequestBody Todo todo) {
		todo.setUsername(username);
		todo.setId(null);
		return todoRepository.save(todo);
//		Todo createdTodo = todoService.addTodo(username, todo.getDescription(), 
//				todo.getTargetDate(),todo.isDone() );
		
//		return createdTodo;
	}

}