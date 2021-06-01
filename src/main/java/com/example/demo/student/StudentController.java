package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getStudent() {
        return service.getStudent();
    }

    @PostMapping
    public void registerNewStudent( @RequestBody Student student){
        service.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public  void deleteStudent(@PathVariable("studentId") Long id ){
        service.deleteStudent(id);
    }


    @Transactional
    public  void updateStudent(@RequestBody Student student){
        service.updateStudent(student);
    }


}
