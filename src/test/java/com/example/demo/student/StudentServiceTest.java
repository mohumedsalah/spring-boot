package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


    StudentService underTest;
    @Mock
    StudentRepository studentRepository;


    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void itShouldGetALlStudent() {
        //given
        //when
        underTest.getStudent();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void itShouldAddNewStudent() {
        //given
        Student st = new Student("temp", "temp@gmail.com",
                LocalDate.of(1996, 2, 22));
        //when
        underTest.addNewStudent(st);
        //then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student arg = studentArgumentCaptor.getValue();
        assertThat(arg).isEqualTo(st);
    }



    @Test
    void itShouldNotAddNewStudentWithRepeatedEmail() {
        //given
        var email =  "temp@gmail.com";
        Student st = new Student("temp", email,
                LocalDate.of(1996, 2, 22));
        Optional<Student> optionalStudent = Optional.of(st);
        given(studentRepository.findStudentByEmail(email)).willReturn(optionalStudent);
        //when


        //then
        assertThatThrownBy(() -> underTest.addNewStudent(st))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email Token");


        verify(studentRepository, never()).save(any());
    }

    @Test
    @Disabled
    void deleteStudent() {
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}