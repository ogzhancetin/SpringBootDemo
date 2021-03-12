package com.example.demo.API;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.DAL.IStudentRepository;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(
			IStudentRepository repository) {
		return args -> {
			
			Student recep = new Student(
				
					"Recep",
					"Recep@gmail.com",
					LocalDate.of(1999, Month.APRIL, 5)						
			);
			
			Student oguz = new Student(
					
					"Oguz",
					"oguz@gmail.com",
					LocalDate.of(1977, Month.APRIL, 6)									
			);
			
			
			Student cetin = new Student(
					
					"Cetin",
					"cetin@gmail.com",
					LocalDate.of(1945, Month.APRIL, 1)								
			);
			
			repository.saveAll(List.of(recep, oguz, cetin));
			
		};		
	}	
}
