package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Department;
import com.rts.tap.service.DepartmentService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@PostMapping(APIConstants.ADD_DEPARTMENT_URL)
	public ResponseEntity<String> addDepartment(@RequestBody Department department) {
		String message = departmentService.addDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@GetMapping(APIConstants.GETALL_DEPARTMENT_URL)
	public List<Department> viewAllDepartments() {
		return departmentService.getAllDepartments();
	}

	@PutMapping("/updatedepartment/{id}")
	public ResponseEntity<String> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
		department.setDepartmentId(id);
		String message = departmentService.updateDepartment(department);
		return ResponseEntity.ok(message);
	}

	@GetMapping(APIConstants.GET_DEPARTMENT_BY_ID_URL)
	public Department getDepartmentById(@PathVariable Long id) {
		return departmentService.getDepartmentById(id);
	}
}