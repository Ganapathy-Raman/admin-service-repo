package com.rts.tap.daoimplementation;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.rts.tap.dao.DepartmentDao;
import com.rts.tap.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

	private EntityManager entityManager;
	
    private static final Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class);
	
	public DepartmentDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Department save(Department department) {
		if (department.getDepartmentId() == null) {
			entityManager.persist(department);
			return department;

		} else {
			return entityManager.merge(department);
		}
	}

	@Override
	public List<Department> getAllDepartments() {
		String hql = "from Department";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public void update(Department department) {
		entityManager.merge(department);
	}

	@Override
	public Department findById(Long id) {
		try {
			return entityManager.find(Department.class, id);
		} catch (Exception e) {
            logger.error("Error finding department with ID: " + id, e);
			return null;
		}
	}

}
