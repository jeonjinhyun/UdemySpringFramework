package com.in28minutes.spring_01.learn_spring_framework_02.game;

@Component
public class PacmanGame implements GamingConsole{
	
	public void up() {
		System.out.println("up");
	}

	public void down() {
		System.out.println("down");
	}
	
	public void left() {
		System.out.println("left");
	}

	public void right() {
		System.out.println("right");
	}

}