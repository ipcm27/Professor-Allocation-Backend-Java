package com.example.recode_final_project.model;

import java.util.Date;


public class Allocation {

    private int id;
    private String dayofweek;
    private String start;
    private String end;
    private Professor professor;
    private Course course;


    public Allocation() {

    }

    public Allocation(int id, String dayofweek, String start, String end, Professor professor, Course course) {
        this.id = id;
        this.dayofweek = dayofweek;
        this.start = start;
        this.end = end;
        this.professor = professor;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}