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
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDTO {

    @NotNull(message = "{validation.firstName.notNull}")
    @NotEmpty(message = "{validation.firstName.notEmpty}")
    private String firstName;

    @NotNull(message = "{validation.lastName.notNull}")
    @NotEmpty(message = "{validation.lastName.notEmpty}")
    private String lastName;

    @NotNull(message = "{validation.email.notNull}")
    @Email(message = "{validation.email.invalid}")
    private String email;

    @NotNull(message = "{validation.contactNumber.notNull}")
    @NotEmpty(message = "{validation.contactNumber.notEmpty}")
    private String contactNumber;

    /**
     * Profile status of the student (e.g., ACTIVE, INACTIVE).
     * This uses an Enum to represent the status.
     */
    @Enumerated(EnumType.STRING)
    private ProfileStatus profileStatus;

    // Optional profile photo (if you want to allow photo updates)
    private byte[] profilePhoto;

}
