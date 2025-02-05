package com.rts.tap.serviceimplementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.OrganizationDao;
import com.rts.tap.exception.OrganizationServiceException;
import com.rts.tap.model.Organization;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {
    
    @Mock
    private OrganizationDao repo;

    @InjectMocks
    private OrganizationServiceImpl organizationService;

    private Organization organization;

    @BeforeEach
    void setUp() {
        organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("Test Organization");
        organization.setOrganizationAddress("123 Test Ave");
        organization.setContactPersonName("John Doe");
        organization.setContactPersonEmail("johndoe@test.com");
        organization.setContactPersonPhone("1234567890");
        organization.setOrganizationWebsiteUrl("http://testorg.com");
    }

    @Test
    void testAddOrganization_Success() {
        when(repo.save(any(Organization.class))).thenReturn(organization);
        
        String result = organizationService.addOrganization(organization);
        
        assertEquals(MessageConstants.ORGANIZATION_ADDED_SUCCESS, result);
        verify(repo, times(1)).save(organization);
    }

    @Test
    void testAddOrganization_Failure() {
        when(repo.save(any(Organization.class))).thenThrow(new DataAccessException("Database Error") { });

        OrganizationServiceException exception = assertThrows(OrganizationServiceException.class, () -> {
            organizationService.addOrganization(organization);
        });

        assertEquals(MessageConstants.FAILED_TO_ADD_ORGANIZATION, exception.getMessage(), "Unexpected message");

        assertNull(exception.getCause(), "Expected no cause for OrganizationServiceException.");

        verify(repo, times(1)).save(organization);
    }

    @Test
    void testGetAllOrganizations_Success() {
        when(repo.getAllOrganization()).thenReturn(Collections.singletonList(organization));
        
        List<Organization> result = organizationService.getAllOrganization();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Organization", result.get(0).getOrganizationName());
    }

    @Test
    void testGetAllOrganizations_EmptyList() {
        when(repo.getAllOrganization()).thenReturn(Collections.emptyList());
        
        List<Organization> result = organizationService.getAllOrganization();
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repo, times(1)).getAllOrganization();
    }

    @Test
    void testGetAllOrganizations_Failure() {
        // Arrange: Mock the repository to throw a DataAccessException
        when(repo.getAllOrganization()).thenThrow(new DataAccessException("Database Access Error") {});

        // Act: Execute the method and expect an OrganizationServiceException
        OrganizationServiceException exception = assertThrows(OrganizationServiceException.class, () -> {
            organizationService.getAllOrganization();
        });

        // Assert: Check the message of the thrown OrganizationServiceException
        assertEquals(MessageConstants.FAILED_TO_RETRIEVE_ORGANIZATIONS, exception.getMessage(), "Unexpected message");

        // Check that the cause is not specifically checked, as per your service impl
        assertNull(exception.getCause(), "Expected no cause for OrganizationServiceException.");
    }
    
    @Test
    void testUpdateOrganization_Success() {
        doNothing().when(repo).update(any(Organization.class));

        String result = organizationService.updateOrganization(organization);
        
        assertEquals(MessageConstants.ORGANIZATION_UPDATED_SUCCESS, result);
        verify(repo, times(1)).update(organization);
    }

    @Test
    void testUpdateOrganization_Failure() {
        doThrow(new DataAccessException("Database Access Error") { }).when(repo).update(any(Organization.class));

        OrganizationServiceException exception = assertThrows(OrganizationServiceException.class, () -> {
            organizationService.updateOrganization(organization);
        });

        assertEquals(MessageConstants.FAILED_TO_UPDATE_ORGANIZATION, exception.getMessage(), "Unexpected message");

        verify(repo, times(1)).update(organization);
    }

    @Test
    void testAddOrganization_InvalidOrganization_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            organizationService.addOrganization(null);
        });
    }

    @Test
    void testUpdateOrganization_InvalidOrganization_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            organizationService.updateOrganization(null);
        });
    }

    @Test
    void testGetAllOrganizations_ThrowsUnexpectedError() {
        // Arrange: Mock the repository to throw a RuntimeException.
        when(repo.getAllOrganization()).thenThrow(new RuntimeException("Unexpected Error"));

        // Act: Assert that getting all organizations throws OrganizationServiceException.
        OrganizationServiceException exception = assertThrows(OrganizationServiceException.class, () -> {
            organizationService.getAllOrganization();
        });

        // Assert: Check that the message from the exception matches what we expect.
        assertEquals(MessageConstants.INTERNAL_SERVER_ERROR, exception.getMessage(), 
                     "Expected message for OrganizationServiceException not found.");
        
        // Ensure the cause is null since we are not passing it in the implementation
        assertNull(exception.getCause(), "Expected no cause for OrganizationServiceException.");
    }

    @Test
    void testUpdateOrganization_ThrowsUnexpectedError() {
        doThrow(new RuntimeException("Unexpected Error")).when(repo).update(any(Organization.class));

        OrganizationServiceException exception = assertThrows(OrganizationServiceException.class, () -> {
            organizationService.updateOrganization(organization);
        });

        assertEquals(MessageConstants.INTERNAL_SERVER_ERROR, exception.getMessage(), 
                "Expected specific message for OrganizationServiceException not found.");

        assertNull(exception.getCause(), "Expected no cause for OrganizationServiceException here.");
    }

    @Test
    void testUpdateOrganization_InvalidOrganization_WithInvalidId() {
        // Set a zero ID to simulate an invalid organization.
        organization.setOrganizationId(0L);
        
        // Assuming validateOrganization is responsible for throwing the IllegalArgumentException.
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            organizationService.updateOrganization(organization);
        });
        
        // Now we can test the exact expected message.
        assertEquals(MessageConstants.INVALID_ORGANIZATION, exception.getMessage(), 
                     "Expected specific message for IllegalArgumentException not found.");
    }
}