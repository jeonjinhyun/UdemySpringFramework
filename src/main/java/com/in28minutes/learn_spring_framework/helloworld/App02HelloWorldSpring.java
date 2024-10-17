package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@SpringBootApplication
public class App02HelloWorldSpring {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(App02HelloWorldSpring.class);

        System.out.println(context.getBean("name"));
        System.out.println(context.getBean("age"));
        System.out.println(context.getBean("person"));
        System.out.println(context.getBean("person2MethodCall"));
        System.out.println(context.getBean("address2"));
        System.out.println(context.getBean(Address.class));
    }
}
