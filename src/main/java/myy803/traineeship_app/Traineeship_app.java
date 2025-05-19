package myy803.traineeship_app;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.CommandLineRunner;



@SpringBootApplication(exclude = { SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class})
public class Traineeship_app implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Traineeship_app.class, args);
	}
	
	public void run(String... args) throws Exception{


	}

}
