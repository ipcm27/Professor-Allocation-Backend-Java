package com.example.recode_final_project.dto;

public class DepartmentDTO {

    int id;
    String name;

    public DepartmentDTO(String name) {
        this.name = name;
    }

    public DepartmentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
