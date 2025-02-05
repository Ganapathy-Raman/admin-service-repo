package com.rts.tap.daoimplementation;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.model.BusinessUnit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class BusinessUnitDaoImpl implements BusinessUnitDao {

	private EntityManager entityManager;
	
    private static final Logger logger = LoggerFactory.getLogger(BusinessUnitDaoImpl.class);

	
	public BusinessUnitDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public BusinessUnit save(BusinessUnit businessUnit) {
		if (businessUnit.getBusinessunitId() == null) {
			entityManager.persist(businessUnit);
			return businessUnit;

		} else {
			return entityManager.merge(businessUnit);
		}
	}

	@Override
    public BusinessUnit findById(Long businessunitId) {
        try {
            return entityManager.find(BusinessUnit.class, businessunitId);
        } catch (Exception e) {
            logger.error("Error finding business unit with ID: " + businessunitId, e);
            return null;
        }
    }

	@Override
	public BusinessUnit getBusinessUnitByLocation(String location) {
		String hql = "FROM BusinessUnit bu WHERE bu.businessUnitLocation = :location";
		TypedQuery<BusinessUnit> query = entityManager.createQuery(hql, BusinessUnit.class);
		query.setParameter("location", location);

		List<BusinessUnit> results = query.getResultList();

		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public List<BusinessUnit> findAll() {
		TypedQuery<BusinessUnit> query = entityManager.createQuery("SELECT b FROM BusinessUnit b", BusinessUnit.class);
		return query.getResultList();
	}

}
