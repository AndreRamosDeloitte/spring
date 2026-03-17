package com.ex1.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex1.spring.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
