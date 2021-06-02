package com.example.demo.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository underTest;

    @Test
    void itShouldFindSavedStudentByEmail() {
        //given
        var email = "temp@gmail.com";
        Student st = new Student("temp", email,
                LocalDate.of(1996, 2, 22));
        underTest.save(st);
        //when
        Optional<Student> studentByEmail = underTest.findStudentByEmail(email);

        //then
        assertThat(studentByEmail.isPresent()).isTrue();
    }
}