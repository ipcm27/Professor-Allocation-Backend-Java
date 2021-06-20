package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class CourseRepositorytest {

	@Autowired
	private CourseRepository courseRepository;

	// READ

	@Test
	public void findAll() {

		// act
		List<Course> courses = courseRepository.findAll();

		// print
		courses.forEach(System.out::println);

	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Course course = courseRepository.findById(id).orElse(null);

		// Print
		System.out.println(course);
	}

	// SAVE & UPDATE

	@Test
	public void save_create() {
		// Arrange
		Course course = new Course();
		course.setId(10L);
		course.setName("Course Of potions");

		// Act
		course = courseRepository.save(course);

		// Print
		System.out.println(course);
	}

	@Test
	public void save_update() {
		
		// Arrange
		Course course = new Course();
		course.setId(1L);
		course.setName("Course 3" );

		// Act
		course = courseRepository.save(course);

		// Print
		System.out.println(course);
	}

	// DELETE

	@Test
	public void deleteById() {
		// Arrange
		Long id = 6L;

		// Act
		courseRepository.deleteById(id);
	}

}
