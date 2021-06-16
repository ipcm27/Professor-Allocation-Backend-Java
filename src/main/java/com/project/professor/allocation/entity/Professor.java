package com.project.professor.allocation.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "professor")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, unique = false)
	private String name;

	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;

	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "departament_id", nullable = false)
	private Departament departament;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "allocationProfessor")
	private List<Allocation> allocations;

	public Professor() {

	}

	public Professor(Long id, String name, String cpf, Departament departament, List<Allocation> allocations) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.departament = departament;
		this.allocations = allocations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Departament getDepartament() {
		return departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

	public List<Allocation> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}

}