package com.darshan.spring_security.controller;

import com.darshan.spring_security.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Hello", "MERN"),
            new Student(2, "new" , "java-MERN")
    ));


    @GetMapping("students")
    public List<Student> getAllStudent(){
        return  students;
    }


    @PostMapping("student")
    public void addStudent(@RequestBody Student student){
        students.add(student);
    }
}
