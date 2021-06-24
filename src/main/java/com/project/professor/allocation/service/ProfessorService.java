package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentRepository departmentRepository;

	public ProfessorService(ProfessorRepository professorRepository,DepartmentRepository departmentRepository ) {
		super();
		this.professorRepository = professorRepository;
		this.departmentRepository = null;
	}
//READ

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);

	}

	public List<Professor> findAll(String name) {
		return professorRepository.findAll();
	}

	public List<Professor> findByName(String name) {
		return professorRepository.findByNameContainingIgnoreCase(name);

	}

	public List<Professor> findByDepartment(Long departmentId) {
		return professorRepository.findByDepartmentId(departmentId);
	}

//Save_Create

	public Professor create(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}
	
	 public Professor update(Professor professor) {
	        Long id = professor.getId();
	        if (id == null || !professorRepository.existsById(id)) {
	           return null;
	        }

	        return saveInternal(professor);
	    }
	 
// Pelo que eu vi na aula e no Slide ambas aas formas de updtae sao corretas.
// prefiro a 1 porque é mais curta e mais fácil de entender

	//public Professor Update(Professor professor) {
		
		//boolean exists = professorRepository.existsById(professor.getId());
		//if(exists) {
			//Professor newProfessor = professorRepository.save(professor);
		
		//Long departmentId = newProfessor.getDepartment().getId();
		//Department newDepartment = departmentRepository.findById(departmentId).orElse(null);
	    //newProfessor.setDepartment(newDepartment);
		
			//return newProfessor;
		
		//} else {
			
			//return null;
		//}
	//}

// DELETE
	public void deleteById(Long id) {
		if (id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	
//AUX METHODS
	private Professor saveInternal(Professor professor) {
		Professor newProfessor = professorRepository.save(professor);
		
		Long departmentId = newProfessor.getDepartment().getId();
		Department newDepartment = departmentRepository.findById(departmentId).orElse(null);
	    newProfessor.setDepartment(newDepartment);
	    
	    return newProfessor;
	}

}
