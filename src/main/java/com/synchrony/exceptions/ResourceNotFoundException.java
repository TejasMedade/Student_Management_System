package com.synchrony.exceptions;

public class ResourceNotFoundException extends Exception {

    // Generated serial version UID for serialization
    private static final long serialVersionUID = 1L;

    // Fields to store resource name, field name, and field value
    private String resourceName;
    private String fieldName;
    private Object fieldValue;  // Changed to Object to support different data types for fieldValue

    /**
     * Constructor for ResourceNotFoundException.
     *
     * @param resourceName The name of the resource (e.g., "Student", "Teacher", etc.)
     * @param fieldName    The field in the resource (e.g., "userName", "id", etc.)
     * @param fieldValue   The value of the field (e.g., specific userId or other field values)
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        // Calls the superclass (Exception) constructor with a formatted error message
        super(String.format("%s Not Found with this %s: %s", resourceName, fieldName, fieldValue));

        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    // Getter methods to access exception details
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
