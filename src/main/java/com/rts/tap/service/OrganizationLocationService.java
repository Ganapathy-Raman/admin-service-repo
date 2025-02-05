package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.OrganizationLocation;

public interface OrganizationLocationService {

	String addOrganizationLocation(OrganizationLocation organizationLocation);

	List<OrganizationLocation> getAllOrganizationLocations();

	OrganizationLocation getOrganizationLocationById(Long id);

	String updateOrganizationLocation(OrganizationLocation organizationLocation);

}
