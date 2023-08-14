package LikeLion11th.YouQuiz_Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class YouQuizProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouQuizProjectApplication.class, args);
	}
}
