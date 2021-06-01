package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getStudent() {
        return repository.findAll();
    }

    public void addNewStudent(Student student) {
        System.out.println(student);
        var obj = repository.findStudentByEmail(student.getEmail());
        if (obj.isPresent()) {
            throw new IllegalArgumentException("Email Token");
        }
        repository.save(student);
    }

    public void deleteStudent(Long id) {
        var obj = repository.findById(id);
        if (!obj.isPresent()) {
            throw new IllegalArgumentException("Id not found");
        }
        repository.deleteById(id);
    }

    public void updateStudent(Student student) {
        var obj = repository.findById(student.getId());
        if (!obj.isPresent()) {
            throw new IllegalArgumentException("Id not found");
        }
        var repeatedOne = repository.findStudentByEmail(student.getEmail());
        if (repeatedOne.isPresent() && repeatedOne.get().getId() != student.getId()) {
            throw new IllegalArgumentException("Email Token");
        }
        repository.save(student);

    }
}
