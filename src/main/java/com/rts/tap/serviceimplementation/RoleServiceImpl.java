package com.rts.tap.serviceimplementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.RoleDao;
import com.rts.tap.exception.RoleServiceException;
import com.rts.tap.model.Role;
import com.rts.tap.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public String addRole(Role role) {
		if (role == null) {
			throw new IllegalArgumentException(MessageConstants.INVALID_ARGUMENT_ERROR);
		}

		try {
			logger.info("Adding role: {}", role);
			roleDao.save(role);
			logger.info(MessageConstants.ROLE_ADDED_SUCCESS);
			return MessageConstants.ROLE_ADDED_SUCCESS;
		} catch (Exception e) {
			logger.error("Failed to add role: {}", e.getMessage(), e);
			throw new RoleServiceException(MessageConstants.ROLE_ADDITION_FAILED);
		}
	}

	@Override
	public List<Role> getAllRole() {
		if (roleDao == null) {
			throw new NullPointerException("roleDao cannot be null");
		}

		try {
			List<Role> roles = roleDao.getAllRole();
			if (roles.isEmpty()) {
				logger.warn(MessageConstants.NO_ROLES_FOUND);
				throw new RoleServiceException(MessageConstants.NO_ROLES_FOUND);
			}
			logger.info("Retrieved roles count: {}", roles.size());
			return roles;
		} catch (Exception e) {
			logger.error("Failed to fetch roles: {}", e.getMessage(), e);
			throw new RoleServiceException(MessageConstants.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public String updateRole(Role role) {
		if (role == null || role.getRoleId() == null) {
			throw new IllegalArgumentException(MessageConstants.INVALID_ARGUMENT_ERROR);
		}

		try {
			logger.info("Updating role: {}", role);
			roleDao.update(role);
			logger.info(MessageConstants.ROLE_UPDATED_SUCCESS);
			return MessageConstants.ROLE_UPDATED_SUCCESS;
		} catch (Exception e) {
			logger.error("Failed to update role: {}", e.getMessage(), e);
			throw new RoleServiceException(MessageConstants.ROLE_UPDATE_FAILED);
		}
	}

	@Override
	public Role getRoleById(Long roleId) {
		if (roleId == null) {
			throw new IllegalArgumentException(MessageConstants.INVALID_ARGUMENT_ERROR);
		}

		try {
			Role role = roleDao.getRoleById(roleId);
			if (role == null) {
				logger.warn("Role with ID {} not found.", roleId);
				throw new RoleServiceException(MessageConstants.ROLE_NOT_FOUND + " for ID: " + roleId);
			}
			return role;
		} catch (DataAccessException e) {
			logger.error("Failed to fetch role by ID {}: {}", roleId, e.getMessage(), e);
			throw new RoleServiceException(MessageConstants.INTERNAL_SERVER_ERROR);
		}
	}
}