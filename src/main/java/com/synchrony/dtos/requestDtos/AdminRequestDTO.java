package com.synchrony.dtos.requestDtos;

import com.synchrony.enums.ProfileStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * This DTO class represents the data required to create or update admin details.
 *
 * @author Tejas_Medade
 */
@Data
@Getter
@Setter
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public ProfileStatus getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(ProfileStatus profileStatus) {
        this.profileStatus = profileStatus;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
