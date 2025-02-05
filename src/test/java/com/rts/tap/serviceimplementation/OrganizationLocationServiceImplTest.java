package com.rts.tap.serviceimplementation;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.OrganizationLocationDao;
import com.rts.tap.exception.OrganizationLocationException;
import com.rts.tap.model.OrganizationLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationLocationServiceImplTest {

    @Mock
    private OrganizationLocationDao organizationLocationDao;

    @InjectMocks
    private OrganizationLocationServiceImpl organizationLocationService;

    private OrganizationLocation location;

    @BeforeEach
    void setUp() {
        location = new OrganizationLocation();
        location.setLocationId(1L);
        location.setLocationName("Test Location");
    }

    @Test
    void testAddOrganizationLocation_Success() {
        when(organizationLocationDao.save(location)).thenReturn(location);

        String result = organizationLocationService.addOrganizationLocation(location);

        assertEquals(MessageConstants.ORG_LOCATION_CREATED_SUCCESS, result);
        verify(organizationLocationDao).save(location);
    }

    @Test
    void testAddOrganizationLocation_Null() {
        OrganizationLocationException exception = assertThrows(OrganizationLocationException.class, () -> {
            organizationLocationService.addOrganizationLocation(null);
        });
        assertEquals(MessageConstants.INVALID_ORG_LOCATION, exception.getMessage());
    }

    @Test
    void testAddOrganizationLocation_Exception() {
        OrganizationLocation location = new OrganizationLocation(); // Make sure to instantiate the Object
        when(organizationLocationDao.save(location)).thenThrow(new RuntimeException("DB error"));

        OrganizationLocationException exception = assertThrows(OrganizationLocationException.class, () -> {
            organizationLocationService.addOrganizationLocation(location);
        });

        // Adjust assertion to only match the expected message without the original exception's message
        assertEquals(MessageConstants.ORG_LOCATION_CREATION_FAILED, exception.getMessage());
    }

    @Test
    void testGetAllOrganizationLocations_Success() {
        when(organizationLocationDao.findAll()).thenReturn(Arrays.asList(location));

        assertEquals(Arrays.asList(location), organizationLocationService.getAllOrganizationLocations());
        verify(organizationLocationDao).findAll();
    }

    @Test
    void testGetAllOrganizationLocations_Exception() {
        when(organizationLocationDao.findAll()).thenThrow(new RuntimeException("DB error"));

        OrganizationLocationException exception = assertThrows(OrganizationLocationException.class, () -> {
            organizationLocationService.getAllOrganizationLocations();
        });

        // Adjust expectation to only match the expected message without the original exception's message
        assertEquals(MessageConstants.ORG_LOCATION_RETRIEVAL_FAILED, exception.getMessage());
    }

    @Test
    void testGetOrganizationLocationById_Success() {
        when(organizationLocationDao.findById(1L)).thenReturn(Optional.of(location));

        OrganizationLocation result = organizationLocationService.getOrganizationLocationById(1L);
        assertEquals(location, result);
        verify(organizationLocationDao).findById(1L);
    }

    @Test
    void testGetOrganizationLocationById_Exception() {
        when(organizationLocationDao.findById(1L)).thenThrow(new RuntimeException("DB error"));

        OrganizationLocationException exception = assertThrows(OrganizationLocationException.class, () -> {
            organizationLocationService.getOrganizationLocationById(1L);
        });

        // Adjust assertion to only match the expected message without the original exception's message
        assertEquals(MessageConstants.ORG_LOCATION_RETRIEVAL_FAILED, exception.getMessage());
    }

    @Test
    void testUpdateOrganizationLocation_Success() {
        when(organizationLocationDao.save(location)).thenReturn(location);

        String result = organizationLocationService.updateOrganizationLocation(location);

        assertEquals(MessageConstants.ORG_LOCATION_UPDATED_SUCCESS, result);
        verify(organizationLocationDao).save(location);
    }

    @Test
    void testUpdateOrganizationLocation_Null() {
        OrganizationLocationException exception = assertThrows(OrganizationLocationException.class, () -> {
            organizationLocationService.updateOrganizationLocation(null);
        });

        assertEquals(MessageConstants.INVALID_ORG_LOCATION, exception.getMessage());
    }

    @Test
    void testUpdateOrganizationLocation_Exception() {
        when(organizationLocationDao.save(location)).thenThrow(new RuntimeException("DB error"));

        OrganizationLocationException exception = assertThrows(OrganizationLocationException.class, () -> {
            organizationLocationService.updateOrganizationLocation(location);
        });

        assertEquals(MessageConstants.ORG_LOCATION_UPDATE_FAILED, exception.getMessage());
    }
}