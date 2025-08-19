package com.ecommerce.sb_ecom_project.exception;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    String fieldName;
    int fieldID;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resourceName, String field, String description) {
        super(String.format("%s not found with %s: %s", resourceName, field, description));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = description;
    }

    public ResourceNotFoundException(String resourceName, String field, int fieldID) {
        super(String.format("%s not found with %s: %s", resourceName, field, fieldID));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldID = fieldID;
    }


}
