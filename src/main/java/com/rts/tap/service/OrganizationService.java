package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.Organization;

public interface OrganizationService {

	String addOrganization(Organization organization);

	List<Organization> getAllOrganization();

	String updateOrganization(Organization organization);

}
