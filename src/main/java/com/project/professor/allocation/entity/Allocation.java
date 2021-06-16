package com.project.professor.allocation.entity;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "allocation")
public class Allocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "day", nullable = false, unique = false)
	private DayOfWeek dayOfWeek;

	@Temporal(value = TemporalType.TIME)
	@Column(name = "start", nullable = false, unique = false)
	private Date start;

	@Temporal(value = TemporalType.TIME)
	@Column(name = "end", nullable = false, unique = false)
	private Date end;

	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "allocation_Professor_id", nullable = false)
	private Professor allocationProfessor;

	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "allocation_Course_id", nullable = false)
	private Course allocationCourse;

	public Allocation() {

	}

	public Allocation(Long id, DayOfWeek dayOfWeek, Date start, Date end, Professor allocationProfessor,
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
