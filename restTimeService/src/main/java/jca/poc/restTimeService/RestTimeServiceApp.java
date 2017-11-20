package jca.poc.restTimeService;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMetrics
public class RestTimeServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(RestTimeServiceApp.class, args);
	}
}
