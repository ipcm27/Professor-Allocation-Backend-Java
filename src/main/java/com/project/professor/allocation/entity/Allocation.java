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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Allocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated (value = EnumType.STRING)
	@Column (nullable =false, unique = false)
	private DayOfWeek dayOfWeek;
	
	@Temporal(value = TemporalType.TIME)
	@Column (nullable =false, unique = false)
	private Date start;
	
	@Temporal(value = TemporalType.TIME)
	@Column (nullable =false, unique = false)
	private Date end;
	
	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "allocationProfessor_id", nullable = false)
	/*
	  O Correto aqui é:
	  private Professor allocationProfessor;
	  
	  Desse jeito, allocationProfessor aponta para uma Alocação, quando deveria apontar para Professor
	  Não se esquecer de fazer o get, set e adicionar no construtor
	  Adicionei uma imagem do schema atual para você ver o erro
	 */
	private Allocation allocationProfessor;
	
	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "allocationCourse_id", nullable = false)
	/*
	  O Correto aqui é:
	  private Course allocationCourse;
	  
	  Desse jeito, allocationCourse aponta para uma Alocação, quando deveria apontar para um Curso
	  Não se esquecer de fazer o get, set e adicionar no construtor
	  Adicionei uma imagem do schema atual para você ver o erro
	 */
	private Allocation allocationCourse;
	
	
	public Allocation() {
		
	}
	
	public Allocation(Long id, DayOfWeek dayOfWeek, Date start, Date end) {
		super();
		this.id = id;
		this.dayOfWeek = dayOfWeek;
		this.start = start;
		this.end = end;
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


}