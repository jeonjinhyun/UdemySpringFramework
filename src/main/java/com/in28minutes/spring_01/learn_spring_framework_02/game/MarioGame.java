package com.in28minutes.spring_01.learn_spring_framework_02.game;


@Component
@Primary
public class MarioGame implements GamingConsole{
	
	public void up() {
		System.out.println("Jump");
	}

	public void down() {
		System.out.println("Go into a hole");
	}
	
	public void left() {
		System.out.println("Go back");
	}

	public void right() {
		System.out.println("Accelerate");
	}


}