package com.ex1.spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ex1.spring.model.Student;
import com.ex1.spring.service.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping()
    public List<Student> getAll() {
        return service.findAll();
    }

    @PutMapping()
    public Student create() {
        return service.create("teste", 100);
    }

}
