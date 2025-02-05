package com.rts.tap.dao;

import java.util.List;
import com.rts.tap.model.Role;

public interface RoleDao {

	Role save(Role role);

	List<Role> getAllRole();

	Role getRoleByName(String roleName);

	Role update(Role role);

	Role getRoleById(Long id);

}
