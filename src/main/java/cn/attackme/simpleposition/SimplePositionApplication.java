package cn.attackme.simpleposition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimplePositionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplePositionApplication.class, args);
	}
}
