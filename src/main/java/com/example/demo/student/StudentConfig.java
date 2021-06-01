package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student salah = new Student("Salah",

                    LocalDate.of(1996, 2, 5),
                    "mo@gmail.com");


            Student omar = new Student("Omar",

                    LocalDate.of(1994, 2, 5),
                    "omar@gmail.com");

            Student akram = new Student("ak",
                    LocalDate.of(2000, 2, 5),
                    "ak@gmail.com");


            repository.saveAll(List.of(akram, salah, omar));
        };
    }
}
