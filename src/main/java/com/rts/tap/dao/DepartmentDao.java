package com.rts.tap.dao;

import java.util.List;
import com.rts.tap.model.Department;

public interface DepartmentDao {

	Department save(Department department);

	List<Department> getAllDepartments();

	void update(Department department);

	Department findById(Long id);

}
