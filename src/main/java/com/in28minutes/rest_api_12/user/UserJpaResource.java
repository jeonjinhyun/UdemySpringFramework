package com.in28minutes.rest_api_12.security;

@RestController
public class UserJpaResource {

	private UserRepository userRepository;
	
	private PostRepository postRepository;

	public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	
	//http://localhost:8080/users
	
	//EntityModel
	//WebMvcLinkBuilder
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		return user.get().getPosts();

	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}


	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();   

		return ResponseEntity.created(location).build();

	}

	
}