package com.synchrony.dtos.requestDtos;

import com.synchrony.models.Address;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

/**
 * This DTO class is used to transfer data to and from the front end when interacting with the student entity.
 * Only necessary fields for student creation, viewing, and editing by the student are included here.
 *
 * @author Tejas_Medade
 */
@Data
@Getter
@Setter
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
}
