package com.synchrony.models;

import com.synchrony.enums.AcademicCourse;
import com.synchrony.enums.AcademicStream;
import com.synchrony.enums.ProfileStatus;
import com.synchrony.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
