package com.synchrony.dtos.requestDtos;

import com.synchrony.enums.ProfileStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This DTO class represents the data required to create or update admin details.
 *
 * @author Tejas_Medade
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDTO {

    @NotNull(message = "{validation.firstName.notNull}")
    @NotEmpty(message = "{validation.firstName.notEmpty}")
    public String firstName;

    @NotNull(message = "{validation.lastName.notNull}")
    @NotEmpty(message = "{validation.lastName.notEmpty}")
    public String lastName;

    @NotNull(message = "{validation.email.notNull}")
    @Email(message = "{validation.email.invalid}")
    public String email;

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
    public String password;

    @NotNull(message = "{validation.contactNumber.notNull}")
    @NotEmpty(message = "{validation.contactNumber.notEmpty}")
    public String contactNumber;

    /**
     * Profile status of the student (e.g., ACTIVE, INACTIVE).
     * This uses an Enum to represent the status.
     */
    @Enumerated(EnumType.STRING)
    public ProfileStatus profileStatus;

    // Optional profile photo (if you want to allow photo updates)
    public byte[] profilePhoto;

}
