package com.jaycen.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jaycen.template")
public class SpringJbdcTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJbdcTemplateApplication.class, args);
	}

}
