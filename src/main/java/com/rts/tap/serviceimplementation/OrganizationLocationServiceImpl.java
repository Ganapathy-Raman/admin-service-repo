package com.rts.tap.serviceimplementation;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.OrganizationLocationDao;
import com.rts.tap.exception.OrganizationLocationException;
import com.rts.tap.model.OrganizationLocation;
import com.rts.tap.service.OrganizationLocationService;

@Service
public class OrganizationLocationServiceImpl implements OrganizationLocationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationLocationServiceImpl.class);
	private final OrganizationLocationDao organizationLocationDao;

	public OrganizationLocationServiceImpl(OrganizationLocationDao organizationLocationDao) {
		this.organizationLocationDao = organizationLocationDao;
	}

	@Override
	public String addOrganizationLocation(OrganizationLocation organizationLocation) {
		if (organizationLocation == null) {
			logger.error(MessageConstants.INVALID_ORG_LOCATION);
			throw new OrganizationLocationException(MessageConstants.INVALID_ORG_LOCATION);
		}

		try {
			organizationLocationDao.save(organizationLocation);
			logger.info("Added organization location: {}", organizationLocation);
			return MessageConstants.ORG_LOCATION_CREATED_SUCCESS;
		} catch (Exception e) {
			logger.error("Error adding organization location: {}", e.getMessage(), e);
			throw new OrganizationLocationException(MessageConstants.ORG_LOCATION_CREATION_FAILED);
		}
	}

	@Override
	public List<OrganizationLocation> getAllOrganizationLocations() {
		try {
			List<OrganizationLocation> locations = organizationLocationDao.findAll();

			if (locations == null || locations.isEmpty()) {
				logger.warn(MessageConstants.NO_ORG_LOCATION_FOUND);
				return Collections.emptyList();
			}

			return locations;
		} catch (Exception e) {
			logger.error("Error retrieving organization locations: {}", e.getMessage(), e);
			throw new OrganizationLocationException(MessageConstants.ORG_LOCATION_RETRIEVAL_FAILED);
		}
	}

	@Override
	public OrganizationLocation getOrganizationLocationById(Long id) {
		try {
			return organizationLocationDao.findById(id).orElseThrow(() -> {
				logger.warn(MessageConstants.ORG_LOCATION_NOT_FOUND + " {}", id);
				return new OrganizationLocationException(
						String.format(MessageConstants.ORG_LOCATION_NOT_FOUND + " {}", id));
			});
		} catch (OrganizationLocationException e) {
			logger.warn("Organization location not found with ID {}: {}", id, e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("Error retrieving organization location with ID {}: {}", id, e.getMessage(), e);
			throw new OrganizationLocationException(String.format(MessageConstants.ORG_LOCATION_RETRIEVAL_FAILED));
		}
	}

	@Override
	public String updateOrganizationLocation(OrganizationLocation organizationLocation) {
		if (organizationLocation == null || organizationLocation.getLocationId() == null) {
			throw new OrganizationLocationException(MessageConstants.INVALID_ORG_LOCATION);
		}
		try {
			organizationLocationDao.save(organizationLocation);
			logger.info("Updated organization location: {}", organizationLocation);
			return MessageConstants.ORG_LOCATION_UPDATED_SUCCESS;
		} catch (Exception e) {
			logger.error("Error updating organization location: {}", e.getMessage(), e);
			logger.warn("Throwing custom exception with original exception as cause: {}", e.getMessage());
			throw new OrganizationLocationException(MessageConstants.ORG_LOCATION_UPDATE_FAILED);
		}
	}
}