package com.rts.tap.dao;

import java.util.List;
import com.rts.tap.model.BusinessUnit;

public interface BusinessUnitDao {

	BusinessUnit save(BusinessUnit businessUnit);

	BusinessUnit getBusinessUnitByLocation(String location);

	BusinessUnit findById(Long businessunitId);

	List<BusinessUnit> findAll();

}
