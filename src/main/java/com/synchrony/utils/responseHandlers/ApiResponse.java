package com.synchrony.utils.responseHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse {

    private LocalDateTime timestamp;

    private String message;

    private boolean status;
}
