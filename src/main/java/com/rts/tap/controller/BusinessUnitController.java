package com.rts.tap.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.BusinessUnit;
import com.rts.tap.service.BusinessUnitService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)
public class BusinessUnitController {

	private final BusinessUnitService businessUnitService;
	private static final Logger logger = LoggerFactory.getLogger(BusinessUnitController.class);

	public BusinessUnitController(BusinessUnitService businessUnitService) {
		this.businessUnitService = businessUnitService;
	}

	@PostMapping(APIConstants.ADD_BUSINESSUNIT_URL)
	public ResponseEntity<String> addBusinessUnit(@RequestBody BusinessUnit businessUnit) {
		logger.info("Adding new Business Unit: {}", businessUnit);
		String message = businessUnitService.addBusinessUnit(businessUnit);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@GetMapping(APIConstants.GET_BUSINESSUNIT_BY_LOCATION_URL)
	public ResponseEntity<BusinessUnit> getBusinessUnitByLocation(@PathVariable String location) {
		logger.info("Retrieving Business Unit by location: {}", location);
		BusinessUnit businessUnit = businessUnitService.getBusinessUnitByLocation(location);
		return ResponseEntity.ok(businessUnit);
	}

	@GetMapping(APIConstants.GETALL_BUSINESSUNIT_URL)
	public ResponseEntity<List<BusinessUnit>> getAllBusinessUnits() {
		logger.info("Retrieving all Business Units");
		List<BusinessUnit> businessUnits = businessUnitService.getAllBusinessUnits();
		return ResponseEntity.ok(businessUnits);
	}

	@PutMapping(APIConstants.UPDATE_BUSINESSUNIT_URL)
	public ResponseEntity<String> updateBusinessUnit(@PathVariable Long id, @RequestBody BusinessUnit businessUnit) {
		logger.info("Updating Business Unit with ID: {}", id);
		String message = businessUnitService.updateBusinessUnit(id, businessUnit);
		return ResponseEntity.ok(message);
	}
}