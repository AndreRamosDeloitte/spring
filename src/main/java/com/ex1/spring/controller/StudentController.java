package com.ex1.spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ex1.spring.exceptions.NotFoundException;
import com.ex1.spring.model.Student;
import com.ex1.spring.service.StudentService;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Student getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student.getId(), student.getName(), student.getTestScore());
    }

    @PutMapping("{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        if (student.getId().equals(id))
            throw new NotFoundException("invalid");

        return service.update(id, student.getName(), student.getTestScore());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
