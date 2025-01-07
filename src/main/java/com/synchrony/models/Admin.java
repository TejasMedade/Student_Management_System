package com.synchrony.models;

import com.synchrony.enums.ProfileStatus;
import com.synchrony.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

/**
 * This class represents an Admin entity in the system.
 * It stores details about the admin user, such as username, password, role, and other personal information.
 * The class is annotated with JPA annotations to map it to the database.
 *
 * Author: @Tejas_Medade
 *
 * The Admin class is similar to the Student class but intended for administrative roles.
 * It can manage the system, users, and perform other administrative actions.
 */
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    /**
     * Unique admin ID generated using a custom generator.
     * This ID follows a custom format like the Student ID generator.
     */
    @Id
    @GeneratedValue(generator = "admin-id-generator")
    @GenericGenerator(name = "admin-id-generator", strategy = "com.synchrony.utils.generators.CustomAdminIdGenerator")
    private String userName;

    /**
     * First name of the admin user.
     */
    private String firstName;

    /**
     * Last name of the admin user.
     */
    private String lastName;

    /**
     * Password for the admin user.
     */
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role = UserRole.ROLE_ADMIN;

    /**
     * Email address for the admin user.
     */
    private String email;

    /**
     * Contact number for the admin user.
     */
    private String contactNumber;

    /**
     * Date when the admin user was created.
     */
    private LocalDate createdDate;

    /**
     * Date when the admin user last logged in.
     */
    private LocalDate lastLoginDate;


    /**
     * Date when the admin user was last modified.
     */
    private LocalDate modifiedDate;

    /**
     * Status of the admin user (e.g., ACTIVE, INACTIVE).
     * This can help manage whether an admin account is currently active.
     */
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;


    /**
     * Profile photo of the admin stored as binary data (similar to Student).
     */
    @Lob
    private byte[] profilePhoto;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ProfileStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}

