package com.rts.tap.controller;

import java.util.List;
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
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.Organization;
import com.rts.tap.service.OrganizationService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)
public class OrganizationController {

	private final OrganizationService organizationService;

	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	@PostMapping(APIConstants.ADD_ORGANIZATION_URL)
	public ResponseEntity<String> addOrganization(@RequestBody Organization organization) {
		organizationService.addOrganization(organization);
		return ResponseEntity.ok(MessageConstants.ORGANIZATION_ADDED_SUCCESS);
	}

	@GetMapping(APIConstants.GETALL_ORGANIZATION_URL)
	public ResponseEntity<List<Organization>> viewAllOrganizations() {
		List<Organization> organizations = organizationService.getAllOrganization();
		return ResponseEntity.ok(organizations);
	}

	@PutMapping(APIConstants.UPDATE_ORGANIZATION_URL)
	public ResponseEntity<String> updateOrganization(@PathVariable Long id, @RequestBody Organization organization) {
		organization.setOrganizationId(id);
		String message = organizationService.updateOrganization(organization);
		return ResponseEntity.ok(message);
	}
}