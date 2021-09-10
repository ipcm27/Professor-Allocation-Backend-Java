package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class CourseServiceTest {

	@Autowired
	private CourseRepository courseRepository;

	// Read

	@Test
	public void findAll() {

		// Act
		List<Course> courses = courseRepository.findAll();

		// print
		System.out.println(courses);

	}

	@Test
	public void findById() {
		// Arrange
		Long id = 10L;

		// Act
		Optional<Course> Courses = courseRepository.findById(id);

		// Print
		System.out.println(Courses);
	}

	@Test
	public void findByName() {
		// Arrange
		String name = "Cour";

		// Act
		List<Course> courses = courseRepository.findByNameContainingIgnoreCase(name);

		// Print
		System.out.println(courses);

	}

	//// Create & Update

	@Test
	public void createSave() {

		// Arange
		Course course = new Course();
		course.setId(null);
		course.setName("Course of Italian food");

		// Act
		courseRepository.save(course);

		// Print
		System.out.print(course);
	}

	@Test
	public void createUpdate() {

		// Arrange
		Course course = new Course();
		course.setId(5L);
		course.setName("Course of Spring");

		// Act
		courseRepository.save(course);

		// Print
		System.out.print(course);
	}

	// Delete

	@Test
	public void deleteById() {

		// Arrange
		Long id = 11L;

		// Act
		courseRepository.deleteById(id);

	}
}
