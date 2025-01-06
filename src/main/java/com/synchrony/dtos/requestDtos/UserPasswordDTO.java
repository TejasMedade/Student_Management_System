package com.synchrony.dtos.requestDtos;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for capturing password input with validation.
 *
 * @author Tejas_Medade
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDTO {

    /**
     * Password field with regex validation.
     * It enforces strong password rules:
     * - At least 1 lowercase letter
     * - At least 1 uppercase letter
     * - At least 1 digit
     * - At least 1 special character
     * - Minimum length of 8 characters
     */
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*\\(\\)_\\+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$", message = "{validation.password.pattern}")
    private String password;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*\\(\\)_\\+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$", message = "{validation.password.pattern}")
    private String oldPassword;
}
