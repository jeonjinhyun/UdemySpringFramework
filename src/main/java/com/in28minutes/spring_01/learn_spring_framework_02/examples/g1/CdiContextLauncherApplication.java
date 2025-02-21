package com.in28minutes.spring_01.learn_spring_framework_02.examples.g1;

//@Component
@Named
class BusinessService {
	private DataService dataService;

	//@Autowired
	@Inject
	public void setDataService(DataService dataService) {
		System.out.println("Setter Injection");
		this.dataService = dataService;
	}


	public DataService getDataService() {
		return dataService;
	}

	
	
}

//@Component
@Named
class DataService {
	
}

@Configuration
@ComponentScan
public class CdiContextLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(CdiContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
			
			System.out.println(context.getBean(BusinessService.class)
					.getDataService());

		}
	}
}