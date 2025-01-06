package com.synchrony.exceptions;

/**
 * Custom exception to be thrown when a resource (e.g., student, admin, etc.) is not found in the system.
 * This exception provides details about the missing resource, its field, and the field value that caused the error.
 *
 * @author Tejas_Medade
 */
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

    /**
     * Get the name of the resource that was not found.
     *
     * @return resource name as a String
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Get the name of the field that caused the resource not to be found.
     *
     * @return field name as a String
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Get the value of the field that caused the resource not to be found.
     *
     * @return field value as an Object
     */
    public Object getFieldValue() {
        return fieldValue;
    }
}
