package com.rts.tap.serviceimplementation;

import com.rts.tap.dao.DepartmentDao;
import com.rts.tap.exception.DepartmentException;
import com.rts.tap.model.Department;
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

class DepartmentServiceImplTest {

    @Mock
    private DepartmentDao departmentDaoMock;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDepartmentSuccess() {
        Department department = new Department();
        department.setDepartmentName("HR");
        department.setDescription("Human Resources");

        when(departmentDaoMock.save(department)).thenReturn(department); // Mock the save behavior

        String result = departmentService.addDepartment(department);

        assertEquals(MessageConstants.DEPARTMENT_ADDED_SUCCESS, result);
        verify(departmentDaoMock, times(1)).save(department);
    }

    @Test
    void testAddDepartmentFailure() {
        Department department = new Department();
        department.setDepartmentName("HR");
        department.setDescription("Human Resources");

        // Mocking the exception to be thrown when save is called
        // Simulate a database error by throwing a RuntimeException
        doThrow(new RuntimeException("Database error")).when(departmentDaoMock).save(any(Department.class));

        // Call the method and assert that a DepartmentException is thrown
        DepartmentException thrown = assertThrows(DepartmentException.class, () -> {
            departmentService.addDepartment(department);
        });

        // Verify that the exception message is as expected
        assertEquals(MessageConstants.DEPARTMENT_ADDITION_FAILED, thrown.getMessage());
        
        // Verify that the save method of the DAO has been called once
        verify(departmentDaoMock, times(1)).save(department);
    }

    @Test
    void testGetAllDepartmentsSuccess() {
        Department department1 = new Department();
        department1.setDepartmentId(1L);
        department1.setDepartmentName("Finance");

        Department department2 = new Department();
        department2.setDepartmentId(2L);
        department2.setDepartmentName("HR");

        // Mocking the response
        when(departmentDaoMock.getAllDepartments()).thenReturn(List.of(department1, department2));

        List<Department> departments = departmentService.getAllDepartments();
        assertEquals(2, departments.size());
        verify(departmentDaoMock, times(1)).getAllDepartments();
    }

    @Test
    void testGetAllDepartmentsFailure() {
        when(departmentDaoMock.getAllDepartments()).thenThrow(new RuntimeException("Error fetching departments"));

        DepartmentException thrown = assertThrows(DepartmentException.class, () -> {
            departmentService.getAllDepartments();
        });

        // Assert that the exception message matches the expected message.
        assertEquals(MessageConstants.DEPARTMENTS_RETRIEVAL_FAILED, thrown.getMessage());

        // Verify that the repository method was called once.
        verify(departmentDaoMock, times(1)).getAllDepartments();
    }

    @Test
    void testUpdateDepartmentSuccess() {
        Department department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("Finance");
        department.setDescription("Finance Department");

        // No exception should be thrown, and the update method should be called once
        doNothing().when(departmentDaoMock).update(department);

        String result = departmentService.updateDepartment(department);
        assertEquals(MessageConstants.DEPARTMENT_UPDATED_SUCCESS, result);

        verify(departmentDaoMock, times(1)).update(department);
    }

    @Test
    void testUpdateDepartmentFailure() {
        Department department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("Finance");
        department.setDescription("Finance Department");

        // Mocking the exception to be thrown when update is called
        doThrow(new RuntimeException("Database error")).when(departmentDaoMock).update(any());

        DepartmentException thrown = assertThrows(DepartmentException.class, () -> {
            departmentService.updateDepartment(department);
        });

        // Assert that the message only includes the fixed error message
        assertEquals(MessageConstants.DEPARTMENT_UPDATE_FAILED, thrown.getMessage());

        // Verify that the update method was called once
        verify(departmentDaoMock, times(1)).update(department);
    }

    @Test
    void testGetDepartmentByIdSuccess() {
        Long id = 1L;
        Department department = new Department();
        department.setDepartmentId(id);
        department.setDepartmentName("HR");

        when(departmentDaoMock.findById(id)).thenReturn(department);

        Department retrievedDepartment = departmentService.getDepartmentById(id);

        assertEquals(department, retrievedDepartment);
        verify(departmentDaoMock, times(1)).findById(id);
    }

    @Test
    void testGetDepartmentByIdNotFound() {
        Long id = 1L;

        when(departmentDaoMock.findById(id)).thenReturn(null);

        DepartmentException thrown = assertThrows(DepartmentException.class, () -> {
            departmentService.getDepartmentById(id);
        });

        assertEquals(MessageConstants.DEPARTMENT_RETRIEVAL_FAILED, thrown.getMessage());
        verify(departmentDaoMock, times(1)).findById(id);
    }

    @Test
    void testGetDepartmentByIdFailure() {
        Long id = 1L;

        when(departmentDaoMock.findById(id)).thenThrow(new RuntimeException("Error retrieving department"));

        DepartmentException thrown = assertThrows(DepartmentException.class, () -> {
            departmentService.getDepartmentById(id);
        });

        assertEquals(MessageConstants.DEPARTMENT_RETRIEVAL_FAILED, thrown.getMessage());
        verify(departmentDaoMock, times(1)).findById(id);
    }
}