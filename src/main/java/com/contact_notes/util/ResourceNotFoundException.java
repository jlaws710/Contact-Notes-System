package com.contact_notes.util;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Object resourceId) {
        super(String.format("%s with ID %s not found", resourceName, resourceId));
    }
}
