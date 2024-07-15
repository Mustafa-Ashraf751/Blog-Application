package com.BlogApplication.Bloging.application.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogingApplicationApiApplication {

  @Bean
  public ModelMapper createModel(){
    return new ModelMapper();
  }

	public static void main(String[] args) {
		SpringApplication.run(BlogingApplicationApiApplication.class, args);
	}

}
