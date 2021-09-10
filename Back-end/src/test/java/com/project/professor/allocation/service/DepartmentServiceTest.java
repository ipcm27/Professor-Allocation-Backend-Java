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
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class DepartmentServiceTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
//Read
	
	@Test
	public void findAll() {
		
		//Act
		List<Department> department = departmentRepository.findAll();
		
		//print
		System.out.println(department);
		
	}
	
	@Test
	public void findById(){
		//Arrange
		Long id = 10L;
		
		//Act
		Optional<Department> departments = departmentRepository.findById(id);
		
		//Print
		System.out.println(departments);
	}
	
	@Test
	public void findByName() {
		//Arrange
		String name = "Dep";
		
		//Act
		List<Department> departments = departmentRepository.findByNameContainingIgnoreCase(name);
		
		//Print
		System.out.println(departments);
		
	}
	
////Create & Update

	@Test
	public void createSave() {
		//Arange
	
		Department department = new Department();
		department.setId(null);
		department.setName("Department of XYZ");

		//Act
		departmentRepository.save(department);
		
		//Print
		System.out.print(department);
	}
	
	@Test
	public void createUpdate() {
		
		//Arrange
		Department department = new Department();
		department.setId(16L);
		department.setName("Department of Culinary");
		
		
		//Act
		departmentRepository.save(department);
		
		//Print
		System.out.print(department);
	}
	
	
//Delete
	
	@Test
	public void deleteById(){
		
		//Arrange
		Long id = 15L;
	
		//Act
		departmentRepository.deleteById(id);
		
		
	}
	
	
	
	
	
	

}
