package com.example.recode_final_project.dto;

public class CourseDTO {

    int id;
    String name;

    public CourseDTO(String name) {
        this.name = name;
    }

    public CourseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}