/**
 * AdminResponseDTO is a Data Transfer Object (DTO) representing the response data for admin-related operations.
 * It contains fields related to admin details such as username, profile status, and profile photo.
 *
 * @author Tejas_Medade
 */
package com.synchrony.dtos.responseDtos;

import com.synchrony.enums.ProfileStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * AdminResponseDTO contains the necessary information about an admin, including
 * personal details, profile status, profile photo, and date-related fields for
 * tracking the creation and modification of the admin's data.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO {

    /**
     * The unique username of the admin.
     */
    private String userName;

    /**
     * The first name of the admin.
     */
    private String firstName;

    /**
     * The last name of the admin.
     */
    private String lastName;

    /**
     * The email address of the admin.
     */
    private String email;

    /**
     * The contact number of the admin.
     */
    private String contactNumber;

    /**
     * The profile status of the admin (active, inactive, etc.).
     */
    private ProfileStatus profileStatus;

    /**
     * The profile photo of the admin, stored as a byte array.
     */
    private byte[] profilePhoto;

    /**
     * The date the admin account was created.
     */
    private LocalDate createdDate;

    /**
     * The date the admin last logged in.
     */
    private LocalDate lastLoginDate;

    /**
     * The date the admin details were last modified.
     */
    private LocalDate modifiedDate;
}
