package com.in28minutes.spring_01.learn_spring_framework_02.examples.h1;

public class XmlConfigurationContextLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
			
			System.out.println(context.getBean("name"));
			
			System.out.println(context.getBean("age"));
			
			context.getBean(GameRunner.class).run();

		}
	}