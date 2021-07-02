package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;

@RestController
@RequestMapping(path = "/professors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfessorController {

	private ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}

	@GetMapping(path = "/")
	public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "partName", required = false) String partName) {
		List<Professor> professors = professorService.findAll(partName);
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}

	@GetMapping(path = "/{professor_id}")
	public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id) {
		Professor professor = professorService.findById(id);
		if (professor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(professor, HttpStatus.OK);
		}
	}

	@PostMapping(path = "/")
	public ResponseEntity<Professor> create(@RequestBody Professor professor) {
		try {
			Professor newProfessor = professorService.create(professor);
			return new ResponseEntity<Professor>(newProfessor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = "/{professor_id}")
	public ResponseEntity<Professor> update(@PathVariable(name = "professor_id") Long id,
			@RequestBody Professor professor) {
		professor.setId(id);
		try {
			Professor newProfessor = professorService.update(professor);
			if (newProfessor == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(newProfessor, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{professor_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) {
		professorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void>deleteAll(){
	professorService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}
	
}
