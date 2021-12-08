package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
	
	// Não precisa, já existe na interface JpaRepository
	//List<Allocation> findAll();
	
	//Não precisa, já existe na interface CrudRepository
	//Optional<Allocation> findById(Long id);
	
	// Select * from allocation where professor_id = X);
	List<Allocation> findByAllocationProfessorId(Long id);
	
	// Se eu só botar findByProfessorId ele nao vai buscar na classe professor;
	// Logo eu tenho que por find by um atributo da classe alocaçao, que nesse caso é allocationProfessor
	// Dúvida: é necessário o id em allocatinProfessor-Id-  já que o nome desse atributo é sem o id?

	List<Allocation> findByAllocationCourseId(Long id);
}
