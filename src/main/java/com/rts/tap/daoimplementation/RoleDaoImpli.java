package com.rts.tap.daoimplementation;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.rts.tap.dao.RoleDao;
import com.rts.tap.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class RoleDaoImpli implements RoleDao {

	private EntityManager entityManager;

	public RoleDaoImpli(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Role save(Role role) {
		if (role.getRoleId() == null) {
			entityManager.persist(role);
			return role;

		} else {
			return entityManager.merge(role);
		}
	}

	@Override
	public List<Role> getAllRole() {
		String hql = "from Role";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public Role update(Role role) {
		return entityManager.merge(role);
	}

	public Role getRoleById(Long id) {
		return entityManager.find(Role.class, id);
	}

	public Role getRoleByName(String roleName) {
		return entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class)
				.setParameter("roleName", roleName).getSingleResult();

	}

}
