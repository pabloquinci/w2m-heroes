package com.w2m;

import com.w2m.configuration.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import(value= {AppConfig.class})

public class AppW2mHeoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppW2mHeoresApplication.class, args);
	}

}
