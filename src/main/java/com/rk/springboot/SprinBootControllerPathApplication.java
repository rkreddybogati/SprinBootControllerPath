package com.rk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@SpringBootApplication
@ComponentScan(basePackages = "com.rk.springboot")
public class SprinBootControllerPathApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprinBootControllerPathApplication.class, args);
	}


}
