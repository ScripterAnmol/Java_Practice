package com.example.HelloMaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloMavenApplication {

	public static void main(String[] args) {

		SpringApplication.run(HelloMavenApplication.class, args);
		System.out.println("Hello, Maven!");
		System.out.println("We have Successfully Developed a Maven Application!");
	}

}
