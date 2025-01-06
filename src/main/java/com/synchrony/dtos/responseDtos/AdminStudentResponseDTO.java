/**
 * AdminStudentResponseDTO is a Data Transfer Object (DTO) representing the response data
 * for student-related operations in the admin context. It contains fields specific to a student's
 * academic details, profile status, and other relevant information.
 *
 * @author Tejas_Medade
 */
package com.synchrony.dtos.responseDtos;

import com.synchrony.enums.ProfileStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

/**
 * AdminStudentResponseDTO contains information related to a student's academic details,
 * profile status, and other relevant information managed by the admin.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStudentResponseDTO {

    /**
     * The roll number of the student, typically used as a unique identifier in academic contexts.
     */
    private String rollNo;

    /**
     * The academic stream the student belongs to, such as Science, Arts, Commerce, etc.
     */
    private String academicStream;

    /**
     * The academic course that the student is enrolled in, such as B.Tech, MBA, etc.
     */
    private String academicCourse;

    /**
     * The batch year of the student, represented as a `Year` object (e.g., 2023).
     */
    private Year batchYear;

    /**
     * The class division of the student, such as A, B, C, etc.
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
     * Any remarks related to the student's performance, status, or other observations.
     */
    private String remarks;
}
