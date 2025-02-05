package com.rts.tap.serviceimplementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants; // Add import for MessageConstants
import com.rts.tap.dao.DepartmentDao;
import com.rts.tap.exception.DepartmentException;
import com.rts.tap.model.Department;
import com.rts.tap.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentDao repo;
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	public DepartmentServiceImpl(DepartmentDao repo) {
		this.repo = repo;
	}

	@Override
	public String addDepartment(Department department) {
	    try {
	        repo.save(department);
	        logger.info(MessageConstants.DEPARTMENT_ADDED_SUCCESS + ": {}", department);
	        return MessageConstants.DEPARTMENT_ADDED_SUCCESS;
	    } catch (IllegalArgumentException e) {
	        logger.error(MessageConstants.INVALID_DEPARTMENT_DATA + ": {}", e.getMessage(), e);
	        throw new DepartmentException(MessageConstants.INVALID_DEPARTMENT_DATA);
	    } catch (Exception e) {
	        logger.error(MessageConstants.DEPARTMENT_ADDITION_FAILED + ": {}", e.getMessage(), e);
	        throw new DepartmentException(MessageConstants.DEPARTMENT_ADDITION_FAILED);
	    }
	}

	@Override
	public List<Department> getAllDepartments() {
	    try {
	        List<Department> departments = repo.getAllDepartments();

	        // Check if the retrieved list is null or empty.
	        if (departments == null || departments.isEmpty()) {
	            logger.warn(MessageConstants.NO_DEPARTMENTS_FOUND);
	            throw new DepartmentException(MessageConstants.NO_DEPARTMENTS_FOUND);
	        }

	        logger.info(MessageConstants.DEPARTMENTS_RETRIEVED_SUCCESS + ": {}", departments);
	        return departments;
	    } catch (Exception e) {
	        logger.error(MessageConstants.DEPARTMENTS_RETRIEVAL_FAILED + ": {}", e.getMessage(), e);
	        throw new DepartmentException(MessageConstants.DEPARTMENTS_RETRIEVAL_FAILED);  // Include the original message
	    }
	}

	@Override
	public String updateDepartment(Department department) {
	    if (department == null) {
	        logger.error(MessageConstants.NULL_DEPARTMENT_ERROR);
	        throw new DepartmentException(MessageConstants.NULL_DEPARTMENT_ERROR);
	    }

	    try {
	        repo.update(department);
	        logger.info(MessageConstants.DEPARTMENT_UPDATED_SUCCESS + ": {}", department);
	        return MessageConstants.DEPARTMENT_UPDATED_SUCCESS;
	    } catch (Exception e) {
	        logger.error(MessageConstants.DEPARTMENT_UPDATE_FAILED + ": {}", e.getMessage(), e);
	        throw new DepartmentException(MessageConstants.DEPARTMENT_UPDATE_FAILED); 
	    }
	}

	@Override
	public Department getDepartmentById(Long id) {
		try {
			Department department = repo.findById(id);

			if (department == null) {
				logger.warn(MessageConstants.DEPARTMENT_NOT_FOUND + " ID: {}", id);
				throw new DepartmentException(MessageConstants.DEPARTMENT_NOT_FOUND + " with ID: " + id);
			}

			logger.info(MessageConstants.DEPARTMENT_RETRIEVED_SUCCESS + " for ID {}: {}", id, department);
			return department;

		} catch (Exception e) {
			logger.error(MessageConstants.DEPARTMENT_RETRIEVAL_FAILED + " ID: {}: {}", id, e.getMessage(), e);
			throw new DepartmentException(MessageConstants.DEPARTMENT_RETRIEVAL_FAILED);
		}
	}
}