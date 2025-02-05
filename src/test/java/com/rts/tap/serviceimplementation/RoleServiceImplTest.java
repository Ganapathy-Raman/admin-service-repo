package com.rts.tap.serviceimplementation;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.RoleDao;
import com.rts.tap.exception.RoleServiceException;
import com.rts.tap.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

	@Mock
	private RoleDao roleDao;

	@InjectMocks
	private RoleServiceImpl roleService;

	private Role role;

	@BeforeEach
	void setUp() {
		role = new Role();
		role.setRoleId(null); // Set to null for testing the add method
		role.setRoleName("Test Role");
	}

	@Test
	void testAddRole() {
		when(roleDao.save(any(Role.class))).thenReturn(role);

		String result = roleService.addRole(role);
		assertNotNull(result);
		assertEquals(MessageConstants.ROLE_ADDED_SUCCESS, result);
		verify(roleDao, times(1)).save(role);
	}

	@Test
	void testAddRole_Fails() {
		when(roleDao.save(any(Role.class))).thenThrow(new RuntimeException("Error while adding role"));

		RoleServiceException exception = assertThrows(RoleServiceException.class, () -> roleService.addRole(role));
		assertTrue(exception.getMessage().contains(MessageConstants.ROLE_ADDITION_FAILED));
		verify(roleDao, times(1)).save(role);
	}

	@Test
	void testGetAllRole() {
		Role role1 = new Role();
		role1.setRoleId(1L);
		role1.setRoleName("Role 1");

		Role role2 = new Role();
		role2.setRoleId(2L);
		role2.setRoleName("Role 2");

		List<Role> roles = new ArrayList<>();
		roles.add(role1);
		roles.add(role2);

		when(roleDao.getAllRole()).thenReturn(roles);

		List<Role> result = roleService.getAllRole();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Role 1", result.get(0).getRoleName());
		verify(roleDao, times(1)).getAllRole();
	}

	@Test
	void testGetAllRole_Fails() {
		when(roleDao.getAllRole()).thenThrow(new RuntimeException("Error while fetching roles"));

		RoleServiceException exception = assertThrows(RoleServiceException.class, () -> roleService.getAllRole());
		assertTrue(exception.getMessage().contains(MessageConstants.INTERNAL_SERVER_ERROR));
		verify(roleDao, times(1)).getAllRole();
	}

	@Test
	void testUpdateRole() {
		role.setRoleId(1L); // Now set the ID to test update
		when(roleDao.update(any(Role.class))).thenReturn(role);

		String result = roleService.updateRole(role);
		assertNotNull(result);
		assertEquals(MessageConstants.ROLE_UPDATED_SUCCESS, result);
		verify(roleDao, times(1)).update(role);
	}

	@Test
	void testUpdateRole_Fails() {
		role.setRoleId(1L); // Set the ID for the update test
		doThrow(new RuntimeException("Error while updating role")).when(roleDao).update(any(Role.class));

		RoleServiceException exception = assertThrows(RoleServiceException.class, () -> roleService.updateRole(role));
		assertTrue(exception.getMessage().contains(MessageConstants.ROLE_UPDATE_FAILED));
		verify(roleDao, times(1)).update(role);
	}

	@Test
	void testGetRoleById() {
		role.setRoleId(1L); // Set the role ID
		when(roleDao.getRoleById(1L)).thenReturn(role);

		Role result = roleService.getRoleById(1L);
		assertNotNull(result);
		assertEquals("Test Role", result.getRoleName());
		verify(roleDao, times(1)).getRoleById(1L);
	}

	@Test
	void testGetRoleById_Fails() {
	    when(roleDao.getRoleById(1L)).thenThrow(new RuntimeException("Error while fetching role"));

	    RuntimeException exception = assertThrows(RuntimeException.class, () -> roleService.getRoleById(1L));
	    assertTrue(exception.getMessage().contains("Error while fetching role")); // You may need to adjust this message to match the actual message
	    verify(roleDao, times(1)).getRoleById(1L);
	}

	@Test
	void testGetRoleById_NotFound() {
	    when(roleDao.getRoleById(1L)).thenReturn(null);

	    RoleServiceException exception = assertThrows(RoleServiceException.class, () -> roleService.getRoleById(1L));

	    String expectedMessage = MessageConstants.ROLE_NOT_FOUND + " for ID: 1";

	    assertEquals(expectedMessage, exception.getMessage(), "Expected RoleServiceException message not found.");

	    verify(roleDao, times(1)).getRoleById(1L);
	}
}