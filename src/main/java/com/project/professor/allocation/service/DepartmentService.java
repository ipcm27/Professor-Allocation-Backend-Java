package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {

		this.departmentRepository = departmentRepository;
	}

	// READ

	public Department findById(Long id) {
		return departmentRepository.findById(id).orElse(null);

	}

	public List<Department> findByName(String name) {
		return departmentRepository.findByNameContainingIgnoreCase(name);
	}

	public List<Department> findAll(String name) {
		if (name == null) {
			return departmentRepository.findAll();
		} else {
			return departmentRepository.findByNameContainingIgnoreCase(name);
		}
	}

	// CREATE_UPDATE

	public Department save(Department department) {
		department.setId(null);
		return saveInternal(department);
	}

	public Department update(Department department) {
		Long id = department.getId();
		if (id == null || !departmentRepository.existsById(id)){
			return null;
		}
		return saveInternal(department);
	}
	
	
	// DELETE 
	
	 public void deleteById(Long id) {
	        if (id != null && departmentRepository.existsById(id)) {
	            departmentRepository.deleteById(id);
	        }
	    }

	   public void deleteAll() {
	        departmentRepository.deleteAllInBatch();
	    }


	private Department saveInternal(Department department) {
		return departmentRepository.save(department);
	}
}
