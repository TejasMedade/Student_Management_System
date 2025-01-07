package com.synchrony.dtos.requestDtos;

import com.synchrony.enums.ProfileStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
@Getter
@Setter
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


    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getAcademicStream() {
        return academicStream;
    }

    public void setAcademicStream(String academicStream) {
        this.academicStream = academicStream;
    }

    public String getAcademicCourse() {
        return academicCourse;
    }

    public void setAcademicCourse(String academicCourse) {
        this.academicCourse = academicCourse;
    }

    public Year getBatchYear() {
        return batchYear;
    }

    public void setBatchYear(Year batchYear) {
        this.batchYear = batchYear;
    }

    public String getClassDivision() {
        return classDivision;
    }

    public void setClassDivision(String classDivision) {
        this.classDivision = classDivision;
    }

    public ProfileStatus getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(ProfileStatus profileStatus) {
        this.profileStatus = profileStatus;
    }

    public LocalDate getDateOfLeaving() {
        return dateOfLeaving;
    }

    public void setDateOfLeaving(LocalDate dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
