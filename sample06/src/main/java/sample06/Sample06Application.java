package sample06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring_jdbc.xml")
public class Sample06Application {

	public static void main(String[] args) {
		SpringApplication.run(Sample06Application.class, args);
	}
}
