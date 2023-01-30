package com.ashish.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ashish.pack"})
public class EmployeeApiProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApiProjectApplication.class, args);
	}

}
