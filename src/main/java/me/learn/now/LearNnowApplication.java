package me.learn.now;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LearNnowApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearNnowApplication.class, args);
	}

}
