package com.example.recode_final_project.dto;

public class ProfessorDTO {

    String cpf;
    String name;
    DepartmentProfessorDTO department;

    public ProfessorDTO() {
    }

    public ProfessorDTO(String cpf, String name, DepartmentProfessorDTO department) {
        this.cpf = cpf;
        this.name = name;
        this.department = department;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentProfessorDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentProfessorDTO department) {
        this.department = department;
    }
}
