package com.example.recode_final_project.model;

import java.util.List;

public class Department {

    private int id;
    private String name;
    private List<Professor> listProfessors;

    public Department(){

    }

    public Department(int id, String name){
        this.id = id;
        this.name = name;
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

    public List<Professor> getListProfessors() {
        return listProfessors;
    }

    public void setListProfessors(List<Professor> listProfessors) {
        this.listProfessors = listProfessors;
    }
}
