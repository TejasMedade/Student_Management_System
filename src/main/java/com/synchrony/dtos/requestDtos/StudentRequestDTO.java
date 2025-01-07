package com.synchrony.dtos.requestDtos;

import com.synchrony.models.Address;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * This DTO class is used to transfer data to and from the front end when interacting with the student entity.
 * Only necessary fields for student creation, viewing, and editing by the student are included here.
 *
 * @author Tejas_Medade
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

    /**
     * First name of the student.
     */
    @NotNull(message = "{validation.firstName.notNull}")
    @NotEmpty(message = "{validation.firstName.notEmpty}")
    @Pattern(regexp = "^[A-Za-z]+$", message = "{validation.firstName.pattern}")
    public String firstName;

    /**
     * Last name of the student.
     */
    @NotNull(message = "{validation.lastName.notNull}")
    @NotEmpty(message = "{validation.lastName.notEmpty}")
    @Pattern(regexp = "^[A-Za-z]+$", message = "{validation.lastName.pattern}")
    public String lastName;

    /**
     * Age of the student.
     */
    @Min(value = 1, message = "{validation.age.min}")
    @Max(value = 20, message = "{validation.age.max}")
    public int age;

    /**
     * Date of birth of the student.
     */
    @NotNull(message = "{validation.dateOfBirth.notNull}")
    @Past(message = "{validation.dateOfBirth.past}")
    public LocalDate dateOfBirth;

    /**
     * Contact number of the student.
     */
    @NotNull(message = "{validation.contactNumber.notNull}")
    @NotEmpty(message = "{validation.contactNumber.notEmpty}")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "{validation.contactNumber.pattern}")
    public String contactNumber;

    /**
     * Blood group of the student.
     */
    @NotNull(message = "{validation.bloodGroup.notNull}")
    public String bloodGroup;

    /**
     * Father's name.
     */
    public String fatherName;

    /**
     * Mother's name.
     */
    public String motherName;

    /**
     * Permanent address of the student.
     */
    public Address permanentAddress;

    /**
     * Current address of the student.
     */
    public Address currentAddress;

    /**
     * Email address of the student.
     */
    @NotNull(message = "{validation.email.notNull}")
    @NotEmpty(message = "{validation.email.notEmpty}")
    @Email(message = "{validation.email.pattern}")
    public String email;

    /**
     * Guardian's name (if the student's parents are not available).
     */
    public String guardianName;

    /**
     * Guardian's contact number.
     */
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "{validation.guardianContact.pattern}")
    public String guardianContact;

    /**
     * Profile photo of the student (optional).
     */
    public byte[] profilePhoto;
}
