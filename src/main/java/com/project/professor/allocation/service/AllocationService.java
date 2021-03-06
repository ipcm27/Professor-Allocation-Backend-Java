package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.exception.AllocationCollisionException;
import com.project.professor.allocation.repository.AllocationRepository;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class AllocationService {

	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseService courseService) {
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

//READ

	public List<Allocation> findAll() {
		return allocationRepository.findAll();
	}

	public Allocation findById(Long id) {
		return allocationRepository.findById(id).orElse(null);
	}

	public List<Allocation> findByProfessor(Long professorId) {
		return allocationRepository.findByAllocationProfessorId(professorId);
	}

	public List<Allocation> findByCourse(Long courseId) {
		return allocationRepository.findByAllocationCourseId(courseId);
	}

//CREATE_SAVE

	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		return saveInternal(allocation);
	}

	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();
		if (id == null || !allocationRepository.existsById(id)) {
			return null;
		}

		return saveInternal(allocation);
	}
	
//DELETE

	public void deleteById(Long id) {
		if (id != null && allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

//AUX METHODS
	private Allocation saveInternal(Allocation allocation) {
		if (!hasCollision(allocation)) {
			allocation = allocationRepository.save(allocation);

			Professor professor = allocation.getAllocationProfessor();
			professor = professorService.findById(professor.getId());
			allocation.setAllocationProfessor(professor);

			Course course = allocation.getAllocationCourse();
			course = courseService.findById(course.getId());
			allocation.setAllocationCourse(course);

			return allocation;
		} else {
			throw new AllocationCollisionException(allocation);
		}
	}

	

	boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByAllocationProfessorId(newAllocation.getAllocationProfessor().getId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDayOfWeek() == newAllocation.getDayOfWeek()
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}

}
