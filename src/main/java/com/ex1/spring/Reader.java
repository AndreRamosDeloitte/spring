package com.ex1.spring;

import java.util.List;

import org.springframework.batch.infrastructure.item.ItemReader;

import com.ex1.spring.model.Student;
import com.ex1.spring.service.StudentService;

public class Reader implements ItemReader<List<Student>> {

    private StudentService studentService;

    public Reader(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public List<Student> read() throws Exception {
        return studentService.findAll();
    }

}
