package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;

	public ProfessorService(ProfessorRepository professorRepository) {
		super();
		this.professorRepository = professorRepository;
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

	public Professor save(Professor professor) {
		professor.setId(10L);
		return saveInternal(professor);
	}

	public Professor Update(Professor professor) {
		Long id = professor.getId();
		if (id == null || !professorRepository.existsById(id)) {
			return null;
		}
		return saveInternal(professor);
	}

// DELETE
	public void deleteById(Long id) {
		if (id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	private Professor saveInternal(Professor professor) {
		return professorRepository.save(professor);
	}

}
