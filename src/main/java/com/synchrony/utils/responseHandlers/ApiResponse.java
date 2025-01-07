package com.synchrony.utils.responseHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A utility class to standardize the response structure for API responses.
 * <p>
 * This class is used to send consistent response messages, status, and timestamp information
 * from the server to the client. It can be used for both successful and error responses.
 * </p>
 *
 * Author: @Tejas_Medade
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    /**
     * The timestamp when the response is generated.
     * <p>
     * This field captures the exact time when the API response is created.
     * </p>
     */
    private LocalDateTime timestamp;

    /**
     * A message providing additional information about the response.
     * <p>
     * This field contains a message, such as "Operation successful" or "Error occurred",
     * which can provide context to the client.
     * </p>
     */
    private String message;

    /**
     * A boolean value indicating the success or failure of the operation.
     * <p>
     * The 'status' field is used to indicate whether the operation was successful (true)
     * or if there was an issue (false).
     * </p>
     */
    private boolean status;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ApiResponse(LocalDateTime timestamp, String message, boolean status) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
    }
}
