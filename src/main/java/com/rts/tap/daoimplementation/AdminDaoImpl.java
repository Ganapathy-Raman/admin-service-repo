package com.rts.tap.daoimplementation;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import com.rts.tap.dao.AdminDao;
import com.rts.tap.model.Admin;
import java.util.List;

@Repository
@Transactional
public class AdminDaoImpl implements AdminDao {

	private final EntityManager entityManager;
	
	public AdminDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Admin save(Admin admin) {
		if (admin.getAdminId() == null) {
			entityManager.persist(admin);
			return admin;

		} else {
			return entityManager.merge(admin);
		}
	}

	public Admin findEmail(String email) {
		String hql = "FROM Admin WHERE adminEmail = :email";
		TypedQuery<Admin> query = entityManager.createQuery(hql, Admin.class);
		query.setParameter("email", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Admin> findAll() {
		String hql = "FROM Admin";
		TypedQuery<Admin> query = entityManager.createQuery(hql, Admin.class);
		return query.getResultList();
	}

	@Override
	public Admin findById(Long id) {
		return entityManager.find(Admin.class, id);
	}
}