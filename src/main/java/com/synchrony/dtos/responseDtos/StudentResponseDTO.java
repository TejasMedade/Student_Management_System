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
import lombok.*;

import java.time.LocalDate;
import java.time.Year;

/**
 * StudentResponseDTO contains comprehensive student data for responses, including personal, academic, and address information.
 */
@Data
@Getter
@Setter
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianContact() {
        return guardianContact;
    }

    public void setGuardianContact(String guardianContact) {
        this.guardianContact = guardianContact;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

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
