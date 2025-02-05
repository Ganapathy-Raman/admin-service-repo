package com.rts.tap.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageConstants {
    public static final Logger LOGGER = LoggerFactory.getLogger(MessageConstants.class);

    public static final String SUCCESS_MESSAGE = "Success";
    public static final String FAILURE_MESSAGE = "Failed to perform the operation";
    
    public static final String LOGIN_SUCCESS = "Login Success";
    public static final String LOGIN_NOT_FOUND = "Login credentials not found";
    
    public static final String OTP_SENT = "OTP sent to your email.";
    public static final String OTP_VERIFY = "OTP Verified";
    public static final String OTP_RESENT = "OTP Resend";
    
    public static final String EMAIL_EXIST = "Email Exist";
    public static final String EMPLOYEE_STATUS = "Employee INACTIVE";
    
    public static final String ORGIN = "http://localhost:3000";
    
    public static final String DELETE_SUCCESS = "Delete Success";
    public static final String DELETE_FAILURE = "Delete Failure";
    
    public static final String UPDATE_SUCCESS = "Update Success";
    public static final String UPDATE_FAILURE = "Update Failure";
    
    // Admin messages
    public static final String ADMIN_CREATED_SUCCESS = "Admin created successfully!";
    public static final String ADMIN_ADDITION_FAILED = "Failed to create admin";
    public static final String ADMIN_RETRIEVAL_FAILED = "Failed to retrieve admin";
    public static final String ADMIN_NOT_FOUND = "Admin not found for the provided ID";
    public static final String GETTING_ALL_ADMINS = "Retrieving all admins";
    public static final String ADMINS_RETRIEVED_SUCCESS = "Admins retrieved successfully";
    public static final String ADMIN_RETRIEVED_SUCCESS = "Admin retrieved successfully";
    
    // Business unit messages
    public static final String BUSINESSUNIT_ADDED_SUCCESS = "Business unit created successfully.";
    public static final String BUSINESSUNIT_ADDITION_FAILED = "Failed to create business unit.";
    public static final String BUSINESSUNIT_RETRIEVED_SUCCESS = "Business unit retrieved successfully.";
    public static final String BUSINESSUNIT_NOT_FOUND = "No business unit found";
    public static final String BUSINESSUNIT_RETRIEVAL_FAILED = "Failed to retrieve the business unit.";
    public static final String NO_BUSINESSUNITS_FOUND = "No business units found.";
    public static final String BUSINESSUNIT_UPDATED_SUCCESS = "Business unit updated successfully.";
    public static final String BUSINESSUNIT_UPDATE_FAILED = "Failed to update the business unit.";    

    // Department messages
    public static final String DEPARTMENT_ADDED_SUCCESS = "Department created successfully.";
    public static final String DEPARTMENT_ADDITION_FAILED = "Failed to create department.";
    public static final String INVALID_DEPARTMENT_DATA = "Failed to create department: Invalid department data.";
    public static final String DEPARTMENT_RETRIEVED_SUCCESS = "Retrieved Department successfully.";
    public static final String DEPARTMENT_RETRIEVAL_FAILED = "Failed to retrieve the department.";
    public static final String DEPARTMENTS_RETRIEVAL_FAILED = "Failed to retrieve all departments.";
    public static final String DEPARTMENTS_RETRIEVED_SUCCESS = "Retrieved all departments successfully.";
    public static final String NO_DEPARTMENTS_FOUND = "No departments found.";
    public static final String DEPARTMENT_NOT_FOUND = "Department not found";
    public static final String DEPARTMENT_UPDATED_SUCCESS = "Department updated successfully.";
    public static final String DEPARTMENT_UPDATE_FAILED = "Failed to update department.";
    public static final String NULL_DEPARTMENT_ERROR = "Failed to update department: Department object cannot be null.";
    public static final String NULL_ID_ERROR = "Failed to retrieve department: Department ID cannot be null.";
    
    // Organization messages
    public static final String NULL_ORGANIZATION_WARNING = "Attempted to create a null organization.";
    public static final String ADDING_ORGANIZATION = "Adding organization";
    public static final String ORGANIZATION_ADDED_SUCCESS = "Organization created successfully";
    public static final String DATABASE_ACCESS_ERROR = "Database access error";
    public static final String INVALID_ARGUMENT_ERROR = "Invalid argument error";
    public static final String UNEXPECTED_ERROR = "Unexpected error occurred";
    public static final String ORGANIZATIONS_RETRIEVED_SUCCESS = "Retrieved all organizations successfully";
    public static final String INVALID_ORGANIZATION_UPDATE_WARNING = "Cannot update organization: Invalid organization provided";
    public static final String UPDATING_ORGANIZATION = "Updating organization";
    public static final String ORGANIZATION_UPDATED_SUCCESS = "Organization updated successfully";
    public static final String INVALID_ORGANIZATION = "Invalid organization provided";
    public static final String FAILED_TO_RETRIEVE_ORGANIZATIONS = "Failed to retrieve organizations";
    public static final String FAILED_TO_ADD_ORGANIZATION = "Failed to add organization";
    public static final String FAILED_TO_UPDATE_ORGANIZATION = "Failed to update organization"; 
    
    // Organization location messages
    public static final String ORG_LOCATION_CREATED_SUCCESS = "Organization Location created successfully!";
    public static final String ORG_LOCATION_CREATION_FAILED = "Failed to create Organization Location!";
    public static final String ORG_LOCATION_UPDATED_SUCCESS = "Organization Location updated successfully!";
    public static final String ORG_LOCATION_UPDATE_FAILED = "Failed to update Organization Location!";
    public static final String ORG_LOCATION_NOT_FOUND = "Organization Location not found with ID: ";
    public static final String NO_ORG_LOCATION_FOUND = "No Organization Locations found.";
    public static final String ORG_LOCATION_RETRIEVAL_FAILED = "Failed to retrieve organization location";
    public static final String INVALID_ORG_LOCATION = "Invalid organization location or missing ID";
    
    // Role messages
    public static final String NULL_POINTER_EXCEPTION = "NullPointerException while fetching roles";
    public static final String ROLE_ADDED_SUCCESS = "Role created successfully.";
    public static final String ROLE_ADDITION_FAILED = "Failed to create role.";
    public static final String ROLE_UPDATED_SUCCESS = "Role updated successfully.";
    public static final String ROLE_UPDATE_FAILED = "Failed to update the role.";
    public static final String ROLE_NOT_FOUND = "Role not found for the provided ID.";
    public static final String NO_ROLES_FOUND = "No roles found.";

    public static final String INTERNAL_SERVER_ERROR = "Internal server error occurred!";

    private MessageConstants() {
        throw new UnsupportedOperationException("Cannot instantiate utility class");
    }
}