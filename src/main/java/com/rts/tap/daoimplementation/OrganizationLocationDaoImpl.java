package com.rts.tap.daoimplementation;

import com.rts.tap.dao.OrganizationLocationDao;
import com.rts.tap.model.OrganizationLocation;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class OrganizationLocationDaoImpl implements OrganizationLocationDao {
	
	private EntityManager entityManager;

	public OrganizationLocationDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public OrganizationLocation save(OrganizationLocation organizationLocation) {
		if (organizationLocation.getLocationId() == null) {
			entityManager.persist(organizationLocation);
			return organizationLocation;

		} else {
			return entityManager.merge(organizationLocation);
		}
	}

	@Override
	public List<OrganizationLocation> findAll() {
		String hql = "FROM OrganizationLocation";
		return entityManager.createQuery(hql, OrganizationLocation.class).getResultList();
	}

	@Override
	public Optional<OrganizationLocation> findById(Long id) {
		OrganizationLocation organizationLocation = entityManager.find(OrganizationLocation.class, id);
		return Optional.ofNullable(organizationLocation);
	}
}
