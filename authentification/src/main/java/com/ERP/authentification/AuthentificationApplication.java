package com.ERP.authentification;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class AuthentificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
		
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins("http://localhost:4200")
	            .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowCredentials(true);
	        }
	    };
	}
	@Primary
	@Bean
	public TaskExecutor primaryTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// add necessary properties to the executor
		return executor;
	}
	//https://stackoverflow.com/questions/38959545/spring-boot-with-activiti-and-websockets
	//https://stackoverflow.com/questions/38977415/spring-boot-abstract-auto-configuration-problems
}
