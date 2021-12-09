package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

import io.swagger.annotations.Api;
@Api(tags= {"Departments"})
@RestController
@CrossOrigin
@RequestMapping(path = "/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@GetMapping(path = "/")
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "partName", required = false) String partName) {
		List<Department> departments = departmentService.findAll(partName);
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}

	@GetMapping(path = "/{department_id}")
	public ResponseEntity<Department>findById(@PathVariable(name = "department_id") Long id) {
		Department department = departmentService.findById(id);
		if (department == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(department, HttpStatus.OK);

		}
	}

	@PostMapping(path = "/")
	public ResponseEntity<Department> create(@RequestBody Department department) {
		try {
			Department newDepartment = departmentService.create(department);

			return new ResponseEntity<Department>(newDepartment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "/{department_id}")
	public ResponseEntity<Department> update(@PathVariable(name = "department_id") Long id,
			@RequestBody Department department) {
		department.setId(id);
		try {
			Department newDepartment = departmentService.update(department);
			if (newDepartment == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(newDepartment, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}

	@DeleteMapping(path = "/{department_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id) {
		departmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		departmentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
