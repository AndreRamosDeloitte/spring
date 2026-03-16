package com.ex1.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ex1.spring.model.Student;
import com.ex1.spring.repositories.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(String name, int testScore) {
        Student student = new Student(name, testScore);
        repository.save(student);
        return student;
    }

    public List<Student> findAll() {

        List<Student> students = repository.findAll();
        return students;
    }
}