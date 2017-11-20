package jca.poc.restAccessService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"jca.poc.serviceCommons.healthChecker.**"})
public class RestAccessServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(RestAccessServiceApp.class, args);
	}
}
