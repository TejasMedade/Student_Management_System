package com.synchrony.models;

import com.synchrony.enums.AcademicCourse;
import com.synchrony.enums.AcademicStream;
import com.synchrony.enums.ProfileStatus;
import com.synchrony.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.Year;

/**
 * This class represents a Student entity in the system.
 * It stores various details about the student, including personal information, academic details, and address information.
 * The class is annotated with JPA annotations to map it to the database.
 * <p>
 * Author: @Tejas_Medade
 * <p>
 * The student can have details like roll number, profile photo, contact information, academic stream, and address.
 * Additional fields like remarks, email, and guardian information are included to give a complete view of a student.
 */
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    /**
     * Unique username for the student. It is generated using a custom generator.
     */
    @Id
    @GeneratedValue(generator = "student-id-generator")
    @GenericGenerator(name = "student-id-generator", strategy = "com.synchrony.utils.generators.CustomStudentIdGenerator")
    private String userName;

    /**
     * Roll number assigned to the student.
     */
    private String rollNo;

    /**
     * First name of the student.
     */
    private String firstName;

    /**
     * Last name of the student.
     */
    private String lastName;

    /**
     * Profile photo of the student stored as binary data.
     * The @Lob annotation indicates that this is a large object, typically a file or image.
     */
    @Lob
    private byte[] profilePhoto;

    /**
     * Age of the student.
     */
    private int age;

    /**
     * Password for the student to access the system.
     */
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role = UserRole.ROLE_STUDENT;

    /**
     * Date of birth of the student.
     */
    private LocalDate dateOfBirth;

    /**
     * Date of registration of the student in the system.
     */
    private LocalDate dateOfRegistration;

    /**
     * Contact number of the student.
     */
    private String contactNumber;

    /**
     * Blood group of the student.
     */
    private String bloodGroup;

    /**
     * Emergency contact number for the student.
     */
    private String emergencyContact;

    /**
     * Profile status of the student (e.g., ACTIVE, INACTIVE).
     * This uses an Enum to represent the status.
     */
    @Enumerated(EnumType.STRING)
    private ProfileStatus profileStatus;

    /**
     * Date when the student leaves the system (e.g., graduation date, if applicable).
     */
    private LocalDate dateOfLeaving;

    /**
     * Father's name.
     */
    private String fatherName;

    /**
     * Mother's name.
     */
    private String motherName;

    /**
     * Father's contact number.
     */
    private String fatherContact;

    /**
     * Mother's contact number.
     */
    private String motherContact;

    /**
     * Permanent address of the student. This is an embedded address object with its own fields.
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "addressLine_1", column = @Column(name = "permanent_address_line_1")),
            @AttributeOverride(name = "addressLine_2", column = @Column(name = "permanent_address_line_2")),
            @AttributeOverride(name = "landMark", column = @Column(name = "permanent_landmark")),
            @AttributeOverride(name = "city", column = @Column(name = "permanent_city")),
            @AttributeOverride(name = "district", column = @Column(name = "permanent_district")),
            @AttributeOverride(name = "state", column = @Column(name = "permanent_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "permanent_zip_code"))
    })
    private Address permanentAddress;

    /**
     * Current address of the student. This is an embedded address object with its own fields.
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "addressLine_1", column = @Column(name = "current_address_line_1")),
            @AttributeOverride(name = "addressLine_2", column = @Column(name = "current_address_line_2")),
            @AttributeOverride(name = "landMark", column = @Column(name = "current_landmark")),
            @AttributeOverride(name = "city", column = @Column(name = "current_city")),
            @AttributeOverride(name = "district", column = @Column(name = "current_district")),
            @AttributeOverride(name = "state", column = @Column(name = "current_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "current_zip_code"))
    })
    private Address currentAddress;

    /**
     * Division or class assigned to the student (e.g., "A", "B", etc.).
     */
    private String classDivision;

    /**
     * Academic stream the student is enrolled in (e.g., Arts, Commerce, Science).
     * This is an enum field.
     */
    @Enumerated(EnumType.STRING)
    private AcademicStream academicStream;

    /**
     * Academic course the student is pursuing (e.g., B.A, B.Sc, B.Com).
     */
    @Enumerated(EnumType.STRING)
    private AcademicCourse academicCourse;

    /**
     * Date when the student record was last modified.
     */
    private LocalDate modifiedDate;

    /**
     * Date when the student record was created.
     */
    private LocalDate createdDate;

    /**
     * Date when the admin user last logged in.
     */
    private LocalDate lastLoginDate;

    /**
     * Batch or year the student is in (e.g., Batch of 2025).
     */
    private Year batchYear;

    /**
     * Remarks or comments from teachers or administrators about the student.
     */
    private String remarks;

    /**
     * Email address of the student.
     */
    private String email;

    /**
     * Guardian's name (e.g., if the student's parents are not available).
     */
    private String guardianName;

    /**
     * Guardian's contact number.
     */
    private String guardianContact;

    @PrePersist
    public void onCreate() {
        LocalDate currentDate = LocalDate.now();
        if (this.createdDate == null) {
            this.createdDate = currentDate;
        }
        this.modifiedDate = currentDate;
    }

    @PreUpdate
    public void onUpdate() {
        this.modifiedDate = LocalDate.now();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

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

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
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

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
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

    public String getFatherContact() {
        return fatherContact;
    }

    public void setFatherContact(String fatherContact) {
        this.fatherContact = fatherContact;
    }

    public String getMotherContact() {
        return motherContact;
    }

    public void setMotherContact(String motherContact) {
        this.motherContact = motherContact;
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

    public String getClassDivision() {
        return classDivision;
    }

    public void setClassDivision(String classDivision) {
        this.classDivision = classDivision;
    }

    public AcademicStream getAcademicStream() {
        return academicStream;
    }

    public void setAcademicStream(AcademicStream academicStream) {
        this.academicStream = academicStream;
    }

    public AcademicCourse getAcademicCourse() {
        return academicCourse;
    }

    public void setAcademicCourse(AcademicCourse academicCourse) {
        this.academicCourse = academicCourse;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Year getBatchYear() {
        return batchYear;
    }

    public void setBatchYear(Year batchYear) {
        this.batchYear = batchYear;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}
