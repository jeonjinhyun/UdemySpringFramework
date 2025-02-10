package com.in28minutes.learn_spring_framework.game;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppgamingBasicJava {
    public static void main(String[] args) {
        var marioGame = new MarioGame();
        var gameRunner = new GameRunner(marioGame);
        gameRunner.run();
    }
}
