package com.rts.tap.serviceimplementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.OrganizationDao;
import com.rts.tap.exception.OrganizationServiceException;
import com.rts.tap.model.Organization;
import com.rts.tap.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private final OrganizationDao repo;
	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	public OrganizationServiceImpl(OrganizationDao repo) {
		this.repo = repo;
	}

	@Override
	public String addOrganization(Organization organization) {
		if (organization == null) {
			throw new IllegalArgumentException(MessageConstants.INVALID_ORGANIZATION);
		}

		validateOrganization(organization);

		try {
			logger.info(MessageConstants.ADDING_ORGANIZATION + ": {}", organization);
			Organization savedOrganization = repo.save(organization);
			logger.info(MessageConstants.ORGANIZATION_ADDED_SUCCESS + ": {}", savedOrganization);
			return MessageConstants.ORGANIZATION_ADDED_SUCCESS;
		} catch (DataAccessException e) {
			logger.error(MessageConstants.DATABASE_ACCESS_ERROR + ": {}", e.getMessage(), e);

			throw new OrganizationServiceException(MessageConstants.FAILED_TO_ADD_ORGANIZATION);
		} catch (Exception e) {
			logger.error(MessageConstants.UNEXPECTED_ERROR + ": {}", e.getMessage(), e);
			throw new OrganizationServiceException(MessageConstants.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Organization> getAllOrganization() {
		try {
			List<Organization> organizations = repo.getAllOrganization();
			logger.info(MessageConstants.ORGANIZATIONS_RETRIEVED_SUCCESS + ". Count: {}", organizations.size());
			return organizations;
		} catch (DataAccessException e) {
			logger.error(MessageConstants.DATABASE_ACCESS_ERROR + ": {}", e.getMessage(), e);
			throw new OrganizationServiceException(MessageConstants.FAILED_TO_RETRIEVE_ORGANIZATIONS);
		} catch (Exception e) {
			logger.error(MessageConstants.UNEXPECTED_ERROR + ": {}", e.getMessage(), e);
			throw new OrganizationServiceException(MessageConstants.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public String updateOrganization(Organization organization) {
		if (organization == null) {
			throw new IllegalArgumentException(MessageConstants.INVALID_ORGANIZATION);
		}

		validateOrganization(organization);

		try {
			logger.info(MessageConstants.UPDATING_ORGANIZATION + ": {}", organization);
			repo.update(organization);
			logger.info(MessageConstants.ORGANIZATION_UPDATED_SUCCESS + ": {}", organization);
			return MessageConstants.ORGANIZATION_UPDATED_SUCCESS;
		} catch (DataAccessException e) {
			logger.error(MessageConstants.DATABASE_ACCESS_ERROR + ": {}", e.getMessage(), e);
			throw new OrganizationServiceException(MessageConstants.FAILED_TO_UPDATE_ORGANIZATION);
		} catch (Exception e) {
			logger.error(MessageConstants.UNEXPECTED_ERROR + ": {}", e.getMessage(), e);
			throw new OrganizationServiceException(MessageConstants.INTERNAL_SERVER_ERROR);
		}
	}

	public void validateOrganization(Organization organization) {
		if (organization.getOrganizationId() <= 0) {
			throw new IllegalArgumentException(MessageConstants.INVALID_ORGANIZATION);
		}

	}
}