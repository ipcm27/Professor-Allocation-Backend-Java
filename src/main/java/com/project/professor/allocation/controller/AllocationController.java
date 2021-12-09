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
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.service.AllocationService;

import io.swagger.annotations.Api;

import com.project.professor.allocation.entity.Allocation;

@Api(tags= {"Allocation"})
@RestController
@CrossOrigin
@RequestMapping(path = "/allocations", produces = MediaType.APPLICATION_JSON_VALUE)
public class AllocationController {

	private AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}

	//READ
	
	@GetMapping(path = "/")
	public ResponseEntity<List<Allocation>> findAll(){
		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	@GetMapping(path = "/{allocation_id}")
	public ResponseEntity<Allocation> findById(@PathVariable(name="allocation_id") Long id) {
		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}
	
	@GetMapping (path = "/{Professor_allocation_id}")
	public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name="Professor_allocation_id") Long id){
		List<Allocation> allocations = allocationService.findByProfessor(id);
		if (allocations == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocations, HttpStatus.OK);
		}
	}
	
	@GetMapping (path = "/{Course_allocation_id}")
	public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name="Course_allocation_id") Long id){
		List<Allocation> allocations = allocationService.findByCourse(id);
		if (allocations == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocations, HttpStatus.OK);
		}
	}

//CREATE
	@PostMapping(path = "/")
	public ResponseEntity<Allocation> create(@RequestBody Allocation allocation) {
		try {
			Allocation newAllocation = allocationService.create(allocation);
			return new ResponseEntity<Allocation>(newAllocation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = "/{professor_id}")
	public ResponseEntity<Allocation> update(@PathVariable(name = "Allocation_id") Long id,
			@RequestBody Allocation allocation) {
		allocation.setId(id);
		try {
			Allocation newAllocation = allocationService.update(allocation);
			if (newAllocation == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(newAllocation, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{allocation_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void>deleteAll(){
	allocationService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}
	
}





