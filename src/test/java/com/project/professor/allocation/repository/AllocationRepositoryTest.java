package com.project.professor.allocation.repository;

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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")


public class AllocationRepositoryTest {
	
	@Autowired
	private AllocationRepository allocationRepository;

	// READ

	@Test
	public void findAll() {
		
		//act
		List <Allocation> allocations = allocationRepository.findAll();
		
		//print
				allocations.forEach(System.out::println);
	}
	
	@Test
	public void findById() {
		//Arrange
		Long id = 10L;
		
		// Act
		Allocation allocation = allocationRepository.findById(id).orElse(null);

		// Print
		System.out.println(allocation);
		
	}
}
