package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.BusinessUnit;

public interface BusinessUnitService {

	String addBusinessUnit(BusinessUnit businessUnit);

	BusinessUnit getBusinessUnitByLocation(String workLocation);

	BusinessUnit getBusinessUnitById(Long businessunitId);

	List<BusinessUnit> getAllBusinessUnits();

	String updateBusinessUnit(Long id, BusinessUnit businessUnit);

}
