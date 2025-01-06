/**
 * StudentResponseDTO is a Data Transfer Object (DTO) that holds the response data related to a student's profile.
 * This DTO contains personal, academic, and address details of the student, which can be used in the response
 * when a student’s information is requested.
 *
 * @author Tejas_Medade
 */
package com.synchrony.dtos.responseDtos;

import com.synchrony.enums.ProfileStatus;
import com.synchrony.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

/**
 * StudentResponseDTO contains comprehensive student data for responses, including personal, academic, and address information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    /**
     * The student's first name.
     */
    private String firstName;

    /**
     * The student's last name.
     */
    private String lastName;

    /**
     * The student's age.
     */
    private int age;

    /**
     * The student's date of birth.
     */
    private LocalDate dateOfBirth;

    /**
     * The student's contact number.
     */
    private String contactNumber;

    /**
     * The student's blood group.
     */
    private String bloodGroup;

    /**
     * The name of the student's father.
     */
    private String fatherName;

    /**
     * The name of the student's mother.
     */
    private String motherName;

    /**
     * The permanent address of the student.
     */
    private Address permanentAddress;

    /**
     * The current address of the student.
     */
    private Address currentAddress;

    /**
     * The student's email address.
     */
    private String email;

    /**
     * The name of the student's guardian (if applicable).
     */
    private String guardianName;

    /**
     * The contact number of the student's guardian.
     */
    private String guardianContact;

    /**
     * The student's profile photo as a byte array.
     */
    private byte[] profilePhoto;

    /**
     * The student's roll number.
     */
    private String rollNo;

    /**
     * The academic stream the student belongs to (e.g., Science, Commerce).
     */
    private String academicStream;

    /**
     * The academic course the student is enrolled in (e.g., B.Tech, MBA).
     */
    private String academicCourse;

    /**
     * The batch year the student belongs to.
     */
    private Year batchYear;

    /**
     * The class division the student belongs to.
     */
    private String classDivision;

    /**
     * The profile status of the student (active, inactive, etc.).
     */
    private ProfileStatus profileStatus;

    /**
     * The date the student left the institution (if applicable).
     */
    private LocalDate dateOfLeaving;

    /**
     * Any additional remarks related to the student (e.g., performance, behavior, etc.).
     */
    private String remarks;
}
