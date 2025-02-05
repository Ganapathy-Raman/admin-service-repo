package com.rts.tap.exception;

public class OrganizationServiceException extends RuntimeException {
    public OrganizationServiceException(String message) {
        super(message);
    }

    public OrganizationServiceException(String message, Throwable cause) {
        super(message, cause); // This initializes the cause
    }
}