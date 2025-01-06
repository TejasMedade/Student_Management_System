package com.synchrony.utils.errorHandlers;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * MyErrorDetails class to standardize error response structure
 * in the application. This will help provide more details when an error occurs.
 *
 * Author: @Tejas_Medade
 */
public class MyErrorDetails {

    // The timestamp when the error occurred
    private LocalDateTime timestamp;

    // A user-friendly message describing the error
    private String message;

    // Detailed description of the error
    private String description;

    // Unique error code to classify the type of error
    private String errorCode;

    // Additional data to provide context to the error (e.g., field-specific errors)
    private Map<String, String> fieldErrors;

    // URL or endpoint that caused the error (optional)
    private String url;

    // List of any nested errors (could be useful for complex cases)
    private List<String> nestedErrors;

    public MyErrorDetails(LocalDateTime timestamp, String message, String description,
                          String errorCode, Map<String, String> fieldErrors,
                          String url, List<String> nestedErrors) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
        this.errorCode = errorCode;
        this.fieldErrors = fieldErrors;
        this.url = url;
        this.nestedErrors = nestedErrors;
    }

    @Override
    public String toString() {
        return "MyErrorDetails [timestamp=" + timestamp + ", message=" + message +
                ", description=" + description + ", errorCode=" + errorCode +
                ", fieldErrors=" + fieldErrors + ", url=" + url + ", nestedErrors=" + nestedErrors + "]";
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getNestedErrors() {
        return nestedErrors;
    }

    public void setNestedErrors(List<String> nestedErrors) {
        this.nestedErrors = nestedErrors;
    }

    // Default constructor
    public MyErrorDetails() {
    }

}
