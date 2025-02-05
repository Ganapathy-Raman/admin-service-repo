package com.rts.tap.serviceimplementation;

import com.rts.tap.dao.AdminDao;
import com.rts.tap.exception.AdminException;
import com.rts.tap.model.Admin;
import com.rts.tap.constants.MessageConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private AdminDao adminDao;

    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    // Test for adding admin successfully
    @Test
    void testAddAdmin() {
        Admin admin = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        when(adminDao.save(any(Admin.class))).thenReturn(admin);
        
        String result = adminServiceImpl.addAdmin(admin);
        
        assertEquals(MessageConstants.ADMIN_CREATED_SUCCESS, result);
        verify(adminDao, times(1)).save(any(Admin.class));
    }

    // Test for failure while adding admin
    @Test
    void testAddAdmin_Fails() {
        Admin admin = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        when(adminDao.save(any(Admin.class))).thenThrow(new RuntimeException("Error while adding admin"));
        
        AdminException thrown = assertThrows(AdminException.class, () -> adminServiceImpl.addAdmin(admin));
        assertEquals(MessageConstants.ADMIN_ADDITION_FAILED, thrown.getMessage());
        verify(adminDao, times(1)).save(any(Admin.class));
    }

    // Test for retrieving all admins successfully
    @Test
    void testGetAllAdmins() {
        Admin admin1 = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        Admin admin2 = new Admin(2L, "adminUsername2", "adminPassword2", "adminEmail2", null, null, null);
        when(adminDao.findAll()).thenReturn(Arrays.asList(admin1, admin2));
        
        List<Admin> result = adminServiceImpl.getAllAdmins();
        
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(adminDao, times(1)).findAll();
    }

    // Test when there are no admins
    @Test
    void testGetAllAdmins_NoUsers() {
        when(adminDao.findAll()).thenReturn(Collections.emptyList());
        
        List<Admin> result = adminServiceImpl.getAllAdmins();
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(adminDao, times(1)).findAll();
    }

    // Test for failure while retrieving all admins
    @Test
    void testGetAllAdmins_Fails() {
        when(adminDao.findAll()).thenThrow(new RuntimeException("Error while retrieving admins"));
        
        AdminException thrown = assertThrows(AdminException.class, adminServiceImpl::getAllAdmins);
        assertEquals(MessageConstants.ADMIN_RETRIEVAL_FAILED, thrown.getMessage());
        verify(adminDao, times(1)).findAll();
    }

    // Test for retrieving admin by ID successfully
    @Test
    void testGetAdminById() {
        Admin admin = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        when(adminDao.findById(1L)).thenReturn(admin);
        
        Admin result = adminServiceImpl.getAdminById(1L);
        
        assertNotNull(result);
        assertEquals(admin, result);
        verify(adminDao, times(1)).findById(1L);
    }

    // Test for retrieving admin by ID not found
    @Test
    void testGetAdminById_NotFound() {
        when(adminDao.findById(1L)).thenReturn(null);
        
        Admin result = adminServiceImpl.getAdminById(1L);
        
        assertNull(result);
        verify(adminDao, times(1)).findById(1L);
    }

    // Test for failure while retrieving admin by ID
    @Test
    void testGetAdminById_Fails() {
        when(adminDao.findById(any(Long.class))).thenThrow(new RuntimeException("Error while retrieving admin with ID"));
        
        AdminException thrown = assertThrows(AdminException.class, () -> adminServiceImpl.getAdminById(1L));
        assertEquals(MessageConstants.ADMIN_RETRIEVAL_FAILED, thrown.getMessage());
        verify(adminDao, times(1)).findById(any(Long.class));
    }

}