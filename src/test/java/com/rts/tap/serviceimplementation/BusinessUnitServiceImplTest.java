package com.rts.tap.serviceimplementation;

import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.exception.BusinessUnitException;
import com.rts.tap.model.BusinessUnit;
import com.rts.tap.constants.MessageConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BusinessUnitServiceImplTest {

    @Mock
    private BusinessUnitDao businessUnitDaoMock;

    @InjectMocks
    private BusinessUnitServiceImpl businessUnitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBusinessUnitSuccess() {
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessUnitName("Sales");
        businessUnit.setDescription("Sales Department");
        businessUnit.setBusinessUnitLocation("New York");

        // Mocking successful save operation
        when(businessUnitDaoMock.save(any(BusinessUnit.class))).thenReturn(businessUnit);

        String result = businessUnitService.addBusinessUnit(businessUnit);

        assertEquals(MessageConstants.BUSINESSUNIT_ADDED_SUCCESS, result);
        verify(businessUnitDaoMock, times(1)).save(businessUnit);
    }

    @Test
    void testAddBusinessUnitFailure() {
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessUnitName("Sales");
        businessUnit.setDescription("Sales Department");
        businessUnit.setBusinessUnitLocation("New York");

        // Mocking exception from save operation
        doThrow(new RuntimeException("Database error")).when(businessUnitDaoMock).save(any());

        BusinessUnitException thrown = assertThrows(BusinessUnitException.class, () -> {
            businessUnitService.addBusinessUnit(businessUnit);
        });

        assertEquals(MessageConstants.BUSINESSUNIT_ADDITION_FAILED, thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).save(businessUnit);
    }

    @Test
    void testGetBusinessUnitByLocationSuccess() {
        String location = "New York";
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessUnitLocation(location);
        businessUnit.setBusinessUnitName("Sales");

        when(businessUnitDaoMock.getBusinessUnitByLocation(location)).thenReturn(businessUnit);

        BusinessUnit retrievedUnit = businessUnitService.getBusinessUnitByLocation(location);
        assertNotNull(retrievedUnit);
        assertEquals("Sales", retrievedUnit.getBusinessUnitName());
        verify(businessUnitDaoMock, times(1)).getBusinessUnitByLocation(location);
    }

    @Test
    void testGetBusinessUnitByLocationNotFound() {
        String location = "Non-existent Location";
        
        when(businessUnitDaoMock.getBusinessUnitByLocation(location)).thenReturn(null);
        
        BusinessUnit retrievedUnit = businessUnitService.getBusinessUnitByLocation(location);
        
        assertNull(retrievedUnit);
        verify(businessUnitDaoMock, times(1)).getBusinessUnitByLocation(location);
    }

    @Test
    void testGetBusinessUnitByLocationFailure() {
        String location = "Some Location";
        when(businessUnitDaoMock.getBusinessUnitByLocation(location)).thenThrow(new RuntimeException("Error retrieving business unit"));

        BusinessUnitException thrown = assertThrows(BusinessUnitException.class, () -> {
            businessUnitService.getBusinessUnitByLocation(location);
        });

        assertEquals(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED, thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).getBusinessUnitByLocation(location);
    }

    @Test
    void testGetBusinessUnitByIdSuccess() {
        Long id = 1L;
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessunitId(id);
        businessUnit.setBusinessUnitName("Sales");

        when(businessUnitDaoMock.findById(id)).thenReturn(businessUnit);

        BusinessUnit retrievedUnit = businessUnitService.getBusinessUnitById(id);
        assertNotNull(retrievedUnit);
        assertEquals("Sales", retrievedUnit.getBusinessUnitName());
        verify(businessUnitDaoMock, times(1)).findById(id);
    }

    @Test
    void testGetBusinessUnitByIdNotFound() {
        Long id = 1L;
        when(businessUnitDaoMock.findById(id)).thenReturn(null);

        BusinessUnitException thrown = assertThrows(BusinessUnitException.class, () -> {
            businessUnitService.getBusinessUnitById(id);
        });

        assertEquals(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED, thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).findById(id);
    }

    @Test
    void testGetBusinessUnitByIdFailure() {
        Long id = 1L;
        when(businessUnitDaoMock.findById(id)).thenThrow(new RuntimeException("Error retrieving business unit"));

        BusinessUnitException thrown = assertThrows(BusinessUnitException.class, () -> {
            businessUnitService.getBusinessUnitById(id);
        });

        assertEquals(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED, thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).findById(id);
    }

    @Test
    void testGetAllBusinessUnitsSuccess() {
        BusinessUnit businessUnit1 = new BusinessUnit();
        businessUnit1.setBusinessunitId(1L);
        businessUnit1.setBusinessUnitName("Sales");

        BusinessUnit businessUnit2 = new BusinessUnit();
        businessUnit2.setBusinessunitId(2L);
        businessUnit2.setBusinessUnitName("Marketing");

        when(businessUnitDaoMock.findAll()).thenReturn(List.of(businessUnit1, businessUnit2));

        List<BusinessUnit> businessUnits = businessUnitService.getAllBusinessUnits();
        assertEquals(2, businessUnits.size());
        verify(businessUnitDaoMock, times(1)).findAll();
    }

    @Test
    void testGetAllBusinessUnits_EmptyList() {
        when(businessUnitDaoMock.findAll()).thenReturn(List.of());

        List<BusinessUnit> businessUnits = businessUnitService.getAllBusinessUnits();
        assertNotNull(businessUnits);
        assertTrue(businessUnits.isEmpty());
        verify(businessUnitDaoMock, times(1)).findAll();
    }

    @Test
    void testGetAllBusinessUnitsFailure() {
        when(businessUnitDaoMock.findAll()).thenThrow(new RuntimeException("Error retrieving all business units"));

        BusinessUnitException thrown = assertThrows(BusinessUnitException.class, () -> {
            businessUnitService.getAllBusinessUnits();
        });

        assertEquals(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED, thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).findAll();
    }

    @Test
    void testUpdateBusinessUnitSuccess() {
        Long id = 1L;
        BusinessUnit existingUnit = new BusinessUnit();
        existingUnit.setBusinessunitId(id);
        existingUnit.setBusinessUnitName("Sales");
        existingUnit.setBusinessUnitLocation("New York");

        BusinessUnit updatedUnit = new BusinessUnit();
        updatedUnit.setBusinessUnitName("Sales Updated");
        updatedUnit.setDescription("Sales Department Updated");
        updatedUnit.setBusinessUnitLocation("San Francisco");

        when(businessUnitDaoMock.findById(id)).thenReturn(existingUnit);

        String result = businessUnitService.updateBusinessUnit(id, updatedUnit);

        assertEquals(MessageConstants.BUSINESSUNIT_UPDATED_SUCCESS, result);
        assertEquals("Sales Updated", existingUnit.getBusinessUnitName());
        assertEquals("Sales Department Updated", existingUnit.getDescription());
        assertEquals("San Francisco", existingUnit.getBusinessUnitLocation());

        verify(businessUnitDaoMock, times(1)).save(existingUnit);
    }

    @Test
    void testUpdateBusinessUnitFailure() {
        Long id = 1L;
        BusinessUnit existingUnit = new BusinessUnit();
        existingUnit.setBusinessunitId(id);
        existingUnit.setBusinessUnitName("Sales");

        BusinessUnit updatedUnit = new BusinessUnit();
        updatedUnit.setBusinessUnitName("Sales Updated");

        when(businessUnitDaoMock.findById(id)).thenReturn(existingUnit);
        doThrow(new RuntimeException("Database error")).when(businessUnitDaoMock).save(existingUnit);

        BusinessUnitException thrown = assertThrows(BusinessUnitException.class, () -> {
            businessUnitService.updateBusinessUnit(id, updatedUnit);
        });

        assertTrue(thrown.getMessage().contains(MessageConstants.BUSINESSUNIT_UPDATE_FAILED));
        verify(businessUnitDaoMock, times(1)).findById(id);
        verify(businessUnitDaoMock, times(1)).save(existingUnit);
    }
}