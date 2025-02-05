package com.rts.tap.daoimplementation;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.OrganizationDao;
import com.rts.tap.model.Organization;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class OrganizationDaoImpl implements OrganizationDao {

	private EntityManager entityManager;
	
	public OrganizationDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Organization save(Organization organization) {
		if (organization.getOrganizationId() == null) {
			entityManager.persist(organization);
			return organization;

		} else {
			return entityManager.merge(organization);
		}
	}

	@Override
	public List<Organization> getAllOrganization() {
		String hql = "from Organization";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public void update(Organization organization) {
		entityManager.merge(organization);

	}

}
