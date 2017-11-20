package jca.poc.restAccessService;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMetrics
public class RestAccessServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(RestAccessServiceApp.class, args);
	}
}
