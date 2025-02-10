package main.java.com.in28minutes.learn_spring_framework;

public class App03GamingSpringBeans {

	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(GamingConfiguration.class)) {

			context.getBean(GamingConsole.class).up();
			
			context.getBean(GameRunner.class).run();

		}
	}
}