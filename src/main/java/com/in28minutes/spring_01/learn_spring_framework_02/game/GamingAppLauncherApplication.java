package com.in28minutes.spring_01.learn_spring_framework_02.game;
@Configuration
@ComponentScan("com.in28minutes.spring_01.learn_spring_framework_02.game")
public class GamingAppLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(GamingAppLauncherApplication.class)) {

			context.getBean(GamingConsole.class).up();
			
			context.getBean(GameRunner.class).run();

		}
	}
}