package com.example.recode_final_project.dto;

import com.example.recode_final_project.model.Course;
import com.example.recode_final_project.model.Professor;

import java.time.DayOfWeek;
import java.util.Date;

public class AllocationDTO {

    private Long id;
    private DayOfWeek dayOfWeek;
    private Date start;
    private Date end;
    private Professor allocationProfessor;
    private Course allocationCourse;


    public AllocationDTO() {

    }

    public AllocationDTO(Long id, DayOfWeek dayOfWeek, Date start, Date end, Professor allocationProfessor,
                         Course allocationCourse) {
        super();
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.start = start;
        this.end = end;
        this.allocationProfessor = allocationProfessor;
        this.allocationCourse = allocationCourse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Professor getAllocationProfessor() {
        return allocationProfessor;
    }

    public void setAllocationProfessor(Professor allocationProfessor) {
        this.allocationProfessor = allocationProfessor;
    }

    public Course getAllocationCourse() {
        return allocationCourse;
    }

    public void setAllocationCourse(Course allocationCourse) {
        this.allocationCourse = allocationCourse;
    }

}