package com.ex1.spring.service;

import com.ex1.spring.exceptions.NotFoundException;
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

    public Student create(Long id, String name, int testScore) {
        Student student = new Student(id, name, testScore);
        return repository.save(student);
    }

    public List<Student> findAll() {

        return repository.findAll();

    }

    public Student findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new NotFoundException("Student not found!"));

    }

    public void delete(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new NotFoundException("Student not found!"));
        repository.delete(student);
    }

    public Student update(Long id, String name, int testScore) {
        Student student = repository.findById(id).orElseThrow(() -> new NotFoundException("Student not found!"));
        student.setName(name);
        student.setTestScore(testScore);
        repository.save(student);
        return student;

    }
}