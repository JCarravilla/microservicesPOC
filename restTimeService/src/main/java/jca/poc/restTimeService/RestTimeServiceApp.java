package jca.poc.restTimeService;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableMetrics
@ComponentScan("jca.poc.serviceCommons")
public class RestTimeServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(RestTimeServiceApp.class, args);
	}
}
