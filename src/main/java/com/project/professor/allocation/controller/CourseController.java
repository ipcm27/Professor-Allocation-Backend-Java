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

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;

import io.swagger.annotations.Api;

@Api(tags= {"Course"})
@RestController
@CrossOrigin
@RequestMapping(path = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController{

	private CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}
	
	
	//Read

	@GetMapping(path = "/")
	public ResponseEntity<List<Course>> findAll(@RequestParam(name = "partName", required = false) String partName) {
		List<Course> courses = courseService.findAll(partName);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@GetMapping(path = "/{Course_id}")
	public ResponseEntity<Course>findById(@PathVariable(name = "Course_id") Long id) {
		Course course = courseService.findById(id);
		if (course== null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(course, HttpStatus.OK);

		}
	}
	
	//Create_Update
	
	@PostMapping(path = "/")
	public ResponseEntity<Course> create(@RequestBody Course course) {
		try {
			Course newCourse = courseService.create(course);

			return new ResponseEntity<Course>(newCourse, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "/{course_id}")
	public ResponseEntity<Course> update(@PathVariable(name = "course_id") Long id,
			@RequestBody Course course) {
		course.setId(id);
		try {
			Course newCourse = courseService.update(course);
			if (newCourse == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(newCourse, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	//Delete 
	
	@DeleteMapping(path = "/{Course_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "Course_id") Long id) {
		courseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void>deleteAll(){
	courseService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}
	
}
	
	
