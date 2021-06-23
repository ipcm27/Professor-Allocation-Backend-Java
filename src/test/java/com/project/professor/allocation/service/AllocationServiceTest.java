package com.project.professor.allocation.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")



public class AllocationServiceTest {

	
	SimpleDateFormat sdf = new SimpleDateFormat ("HH:mm");

	@Autowired
	private AllocationRepository allocationRepository;

//READ

	@Test
	public void findAll() {

		// act
		List<Allocation> allocations = allocationRepository.findAll();

		// print
		allocations.forEach(System.out::println);
		System.out.println("aaaaaaaaaaaa");
	}

	@Test
	public void findById() {
		 //Arrange
		Long id = 1L;

		// Act
		Allocation allocation = allocationRepository.findById(id).orElse(null);

		// Print
		System.out.println(allocation);
	}

	


	// SAVE & UPDATE

	@Test
	public void save_create() throws ParseException {
		
		// Arrange
	    
	    Course course = new Course();
	    course.setId(1L);

	    Professor professor = new Professor();
	    professor.setId(2l);
	     
	    Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setAllocationCourse(course);
		allocation.setAllocationProfessor(professor);
		allocation.setDayOfWeek(DayOfWeek.TUESDAY);
		allocation.setStart(sdf.parse("17:00-0300"));
		allocation.setEnd(sdf.parse("18:00-0300"));
		
		// Act
		allocation = allocationRepository.save(allocation);

		// Print
		System.out.println(allocation);
	}


	@Test
	public void save_update() throws ParseException {
		
	// Arrange
		 Course course = new Course();
		    course.setId(1L);

		    Professor professor = new Professor();
		    professor.setId(2l);
		     
		    Allocation allocation = new Allocation();
			allocation.setId(10L);
			allocation.setAllocationCourse(course);
			allocation.setAllocationProfessor(professor);
			allocation.setDayOfWeek(DayOfWeek.TUESDAY);
			allocation.setStart(sdf.parse("17:00-0300"));
			allocation.setEnd(sdf.parse("18:00-0300"));
			

		// Act
		allocation = allocationRepository.save(allocation);

		// Print
		System.out.println(allocation);
	}

	// DELETE

	@Test
	public void deleteById() {
		// Arrange
		Long id = 2L;

		// Act
		allocationRepository.deleteById(id);
	}

}
	

