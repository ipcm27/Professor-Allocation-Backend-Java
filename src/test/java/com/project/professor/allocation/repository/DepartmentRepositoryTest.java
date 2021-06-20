package com.project.professor.allocation.repository;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;

	// READ
	@Test
	public void findAll() {

		// act
		List<Department> departments = departmentRepository.findAll();

		// print
		departments.forEach(System.out::println);
	}

	@Test
	public void findById() {

		// Arrange
		Long id = 1L;

		// Act
		Department department = departmentRepository.findById(id).orElse(null);

		// print
		System.out.println(department);

	}

	// create
	@Test
	public void save_create() {

		// Arrange
		Department department = new Department();
		department.setId(null);
		department.setName("Departament null");

		// Act
		departmentRepository.save(department);

		// Print
		System.out.print(department);

	}

	@Test
	public void save_update() {

		// Arrange
		Department department = new Department();
		department.setId(13L);
		department.setName("Departament 13L");

		// Act
		departmentRepository.save(department);

		// Print
		System.out.print(department);

	}

	// delete
	@Test
	public void deleteById() {
		// Arrange
		Long id = 12L;

		// Act
		departmentRepository.deleteById(id);
	}
	
	
}
