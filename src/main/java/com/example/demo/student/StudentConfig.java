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
                    "mo@gmail.com",
                    LocalDate.of(1996, 2, 5)
                    );


            Student omar = new Student("Omar",
                    "omar@gmail.com",
                    LocalDate.of(1994, 2, 5)
                    );

            Student akram = new Student("ak",
                    "ak@gmail.com",
                    LocalDate.of(2000, 2, 5)
                    );


            repository.saveAll(List.of(akram, salah, omar));
        };
    }
}
