package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;

	}

//Read

	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	public List<Course> findAll(String name) {
		return courseRepository.findAll();
	}

	public List<Course> findByName(String name) {
		return courseRepository.findByNameContainingIgnoreCase(name);
	}

//Create_Update

	public Course save(Course course) {
		course.setId(10L);
		return saveInternal(course);
	}

	public Course Update(Course course) {
		Long id = course.getId();
		if (id == null || !courseRepository.existsById(id)) {
			return null;
		}
		return saveInternal(course);
	}
	
//Delete
	public void deleteById(Long id) {
		if (id != null && courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}
	
	
//Aux Methods
	private Course saveInternal(Course course) {
		return courseRepository.save(course);
	}

}
