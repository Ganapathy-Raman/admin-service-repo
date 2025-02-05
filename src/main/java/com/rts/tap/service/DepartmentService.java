package com.rts.tap.service;

import java.util.List;
import com.rts.tap.model.Department;

public interface DepartmentService {

	String addDepartment(Department department);

	List<Department> getAllDepartments();

	String updateDepartment(Department department);

	Department getDepartmentById(Long id);

}
