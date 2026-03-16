package com.ex1.spring.model;

import java.util.UUID;

import org.hibernate.annotations.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @Generated
    public UUID id;

    public String name;

    private int testScore;

    public Student() {
    }

    public Student(String name, int testScore) {
        this.name = name;
        this.testScore = testScore;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTestScore() {
        return testScore;
    }

    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }

}
