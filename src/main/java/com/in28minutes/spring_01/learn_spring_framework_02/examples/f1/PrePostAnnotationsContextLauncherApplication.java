package com.in28minutes.spring_01.learn_spring_framework_02.examples.f1;


@Component
class SomeClass {
	
	private SomeDependency someDependency;
	
	public SomeClass(SomeDependency someDependency) {
		super();
		this.someDependency = someDependency;
		System.out.println("All dependencies are ready!");
	}
	
	@PostConstruct
	public void initialize() {
		someDependency.getReady();
	}
	
	@PreDestroy
	public void cleanup() {
		System.out.println("Cleanup");
	}
	
}

@Component 
class SomeDependency {

	public void getReady() {
		System.out.println("Some logic using SomeDependency");
		
	}
	
}

@Configuration
@ComponentScan
public class PrePostAnnotationsContextLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(PrePostAnnotationsContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);

		}
	}
}