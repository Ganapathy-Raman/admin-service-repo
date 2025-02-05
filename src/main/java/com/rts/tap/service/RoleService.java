package com.rts.tap.service;

import java.util.List;
import com.rts.tap.model.Role;

public interface RoleService {

	String addRole(Role role);

	List<Role> getAllRole();

	Role getRoleById(Long id);

	String updateRole(Role role);

}
