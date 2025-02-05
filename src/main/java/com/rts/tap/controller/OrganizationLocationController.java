package com.rts.tap.controller;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.OrganizationLocation;
import com.rts.tap.service.OrganizationLocationService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
public class OrganizationLocationController {

	private final OrganizationLocationService organizationLocationService;

	public OrganizationLocationController(OrganizationLocationService organizationLocationService) {
		this.organizationLocationService = organizationLocationService;
	}

	@PostMapping(APIConstants.ADD_ORG_LOCATION_URL)
	public ResponseEntity<String> addOrganizationLocation(@RequestBody OrganizationLocation organizationLocation) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(organizationLocationService.addOrganizationLocation(organizationLocation));
	}

	@GetMapping(APIConstants.GET_ALL_ORG_LOCATIONS_URL)
	public ResponseEntity<Object> getAllOrganizationLocations() {
		List<OrganizationLocation> locations = organizationLocationService.getAllOrganizationLocations();
		return ResponseEntity.ok(locations);
	}

	@GetMapping(APIConstants.GET_ORG_LOCATION_BY_ID_URL)
	public ResponseEntity<Object> getOrganizationLocationById(@PathVariable Long id) {
		OrganizationLocation location = organizationLocationService.getOrganizationLocationById(id);
		return ResponseEntity.ok(location);
	}

	@PutMapping(APIConstants.UPDATE_ORG_LOCATION_URL)
	public ResponseEntity<String> updateOrganizationLocation(@PathVariable Long id,
			@RequestBody OrganizationLocation organizationLocation) {
		organizationLocation.setLocationId(id);
		return ResponseEntity.ok(organizationLocationService.updateOrganizationLocation(organizationLocation));
	}
}