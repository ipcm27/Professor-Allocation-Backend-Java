package com.project.professor.allocation.service;

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
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class ProfessorServiceTest {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired // A anotação é para cada repositório. Se não colocar vai dar NPE
	private DepartmentRepository departmentRepository;
	
	
//Read 
	@Test
	public void findAll() {
		//act
		
		List <Professor> professors = professorRepository.findAll();
		
		//print
		
		System.out.print(professors);
	}
		
	@Test
	public void findById() {
		//Arrange
		Long id = 1L;
		
		//Act
		Professor professor = professorRepository.findById(id).orElse(null);
		
		//Print
		System.out.println(professor);
	}

	@Test
	public void findByName() {
		
		//Arrange
		String name = "pro";
		
		//Act
		List<Professor> professors = professorRepository.findByNameContainingIgnoreCase(name);
		
		//Print
		
		professors.forEach(System.out::println);
	}
		
//Save,Create and Updtae
	
	
	//Create
	
	@Test
	public void saveCreate() {
		
		//try {
		//Arrange
		Department department = new Department();
		department.setId(7L);
		
		Professor professor = new Professor();
		professor.setId(1L);
		professor.setName("Harry Potter 13");
		professor.setCpf("125.678.123-13");
		professor.setDepartment(department);
		
		//Act
		Professor newProfessor = professorRepository.save(professor);
	    Long departmentId = newProfessor.getDepartment().getId();
	        
	    Department newDepartment = departmentRepository.findById(departmentId).orElse(null);
	    newProfessor.setDepartment(newDepartment);
	 
		//Print
		
		System.out.print(newProfessor);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
		
	}
		
		   @Test
		    public void save_update() {
		        // Arrange
		        Department department = new Department();
		        department.setId(1L);

		        Professor professor = new Professor();
		        professor.setId(2L);
		        professor.setName("Alvus Dumbledore");
		        professor.setCpf("225.689.222-01");
		        professor.setDepartment(department);

		        // Act
		        professor = professorRepository.save(professor);

		        // Print
		        System.out.println(professor);
		    }
			
	//Delete

		    @Test
		    public void deleteById() {
		        // Arrange
		        Long id = 8L;

		        // Act
		        professorRepository.deleteById(id);
		    }
		
	
	}
	
