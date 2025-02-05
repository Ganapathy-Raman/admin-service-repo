package com.rts.tap.dao;

import java.util.List;
import java.util.Optional;
import com.rts.tap.model.OrganizationLocation;

public interface OrganizationLocationDao {

	OrganizationLocation save(OrganizationLocation organizationLocation);

	List<OrganizationLocation> findAll();

	Optional<OrganizationLocation> findById(Long id);

}
