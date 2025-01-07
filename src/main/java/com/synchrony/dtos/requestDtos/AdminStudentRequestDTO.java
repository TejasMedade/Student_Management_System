package com.synchrony.dtos.requestDtos;

import com.synchrony.enums.ProfileStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

/**
 * This DTO class is used by admins to edit certain student-related fields that are not accessible by the student.
 * Admin-only fields such as academic details, class division, etc., are included.
 *
 *
 * @author Tejas_Medade
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStudentRequestDTO {


    /**
     * Roll number assigned to the student.
     */
    @NotNull(message = "{validation.rollNo.notNull}")
    @NotEmpty(message = "{validation.rollNo.notEmpty}")
    public String rollNo;


    /**
     * Academic stream the student is enrolled in (e.g., Arts, Commerce, Science).
     */
    @NotNull(message = "{validation.academicStream.notNull}")
    public String academicStream;

    /**
     * Academic course the student is pursuing (e.g., B.A, B.Sc, B.Com).
     */
    @NotNull(message = "{validation.academicCourse.notNull}")
    public String academicCourse;

    /**
     * Batch or year the student is in (e.g., Batch of 2025).
     */
    @NotNull(message = "{validation.batchYear.notNull}")
    public Year batchYear;

    /**
     * Division or class assigned to the student (e.g., "A", "B", etc.).
     */
    public String classDivision;


    /**
     * Profile status of the student (e.g., ACTIVE, INACTIVE).
     * This uses an Enum to represent the status.
     */
    @Enumerated(EnumType.STRING)
    public ProfileStatus profileStatus;

    /**
     * Date when the student leaves the system (e.g., graduation date, if applicable).
     */
    public LocalDate dateOfLeaving;


    /**
     * Remarks or comments from teachers or administrators about the student.
     */
    public String remarks;
}
