package com.rts.tap.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Role;
import com.rts.tap.service.RoleService;

@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping(APIConstants.ADD_ROLE_URL)
	public ResponseEntity<String> addRole(@RequestBody Role role) {
		String message = roleService.addRole(role);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@GetMapping(APIConstants.GETALL_ROLE_URL)
	public ResponseEntity<List<Role>> viewAllRole() {
		List<Role> roles = roleService.getAllRole();
		return ResponseEntity.ok(roles);
	}

	@GetMapping(APIConstants.GET_ROLE_BY_ID_URL)
	public ResponseEntity<Object> getRoleById(@PathVariable("id") Long id) {
		Role role = roleService.getRoleById(id);
		return ResponseEntity.ok(role);
	}

	@PutMapping(APIConstants.UPDATE_ROLE_URL)
	public ResponseEntity<String> updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
		role.setRoleId(id);
		String message = roleService.updateRole(role);
		return ResponseEntity.ok(message);
	}
}