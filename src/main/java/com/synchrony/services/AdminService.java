package com.synchrony.services;

import com.synchrony.dtos.requestDtos.AdminRequestDTO;
import com.synchrony.dtos.requestDtos.StudentRequestDTO;
import com.synchrony.dtos.requestDtos.UserPasswordDTO;
import com.synchrony.dtos.responseDtos.AdminResponseDTO;
import com.synchrony.dtos.responseDtos.StudentResponseDTO;
import com.synchrony.exceptions.ResourceNotFoundException;
import com.synchrony.utils.responseHandlers.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service interface for handling administrative operations.
 * <p>
 * This interface defines methods for managing student and admin accounts, including adding, editing,
 * deleting accounts, changing passwords, and fetching student details. It also provides methods for
 * searching students based on first or last name.
 * </p>
 *
 * Author: @Tejas_Medade
 */
public interface AdminService {

    /**
     * Get all students in the system.
     * This method retrieves a list of all students from the system.
     *
     * @return List of StudentResponseDTO representing all students
     */
    List<StudentResponseDTO> getAllStudents();

    /**
     * Add a new student account.
     * This method adds a new student to the system and returns the newly created student details.
     *
     * @param studentRequestDTO DTO containing student details
     * @param file              Profile picture of the student
     * @return StudentResponseDTO with the newly created student details
     * @throws IOException if file processing fails
     */
    @Transactional
    StudentResponseDTO addStudentAccount(StudentRequestDTO studentRequestDTO, MultipartFile file) throws IOException;

    /**
     * Delete a student account by user name.
     * This method deletes a student account from the system based on their username.
     *
     * @param userName Unique identifier for the student
     * @throws ResourceNotFoundException if the student is not found
     */
    void deleteStudentAccount(String userName) throws ResourceNotFoundException;

    /**
     * Edit admin account details.
     * This method allows the admin to update their account details.
     *
     * @param adminRequestDTO DTO containing updated admin account details
     * @param file            New profile picture for the admin
     * @param userName        Admin's username to identify which account to edit
     * @return Updated admin account details
     * @throws IOException               if file processing fails
     * @throws ResourceNotFoundException if the admin is not found
     */
    @Transactional
    AdminResponseDTO editAdminAccount(String userName, AdminRequestDTO adminRequestDTO, MultipartFile file) throws IOException, ResourceNotFoundException;

    /**
     * Create a new admin account.
     * This method allows the creation of a new admin account with the provided details.
     *
     * @param adminRequestDTO DTO containing admin account details
     * @param file            Profile picture for the new admin
     * @return Created admin account details
     * @throws IOException if file processing fails
     */
    @Transactional
    AdminResponseDTO createAdminAccount(AdminRequestDTO adminRequestDTO, MultipartFile file) throws IOException;

    /**
     * Delete an admin account by admin username.
     * This method deletes an admin account from the system based on their username.
     *
     * @param adminUsername Admin's username to be deleted
     * @throws ResourceNotFoundException if the admin is not found
     */
    void deleteAdminAccount(String adminUsername) throws ResourceNotFoundException;

    /**
     * Change the password of the admin account.
     * This method allows an admin to change their password.
     *
     * @param userName        The username of the admin
     * @param oldPassword     The current password
     * @param userPasswordDTO DTO containing the new password
     * @return ApiResponse indicating the result of the password change operation
     * @throws ResourceNotFoundException if the admin is not found
     */
    @Transactional
    ApiResponse changeAdminPassword(String userName, String oldPassword, UserPasswordDTO userPasswordDTO) throws ResourceNotFoundException;

    /**
     * Get the details of a student by their unique username.
     * This method retrieves the details of a student based on their username.
     *
     * @param userName The unique username of the student
     * @return The student's details as a StudentResponseDTO
     * @throws ResourceNotFoundException if the student is not found
     */
    StudentResponseDTO getStudentDetailsById(String userName) throws ResourceNotFoundException;

    /**
     * Search for students by their first name.
     * This method allows searching for students whose first name matches the given input.
     *
     * @param firstName The student's first name to search for
     * @return A list of students matching the first name, each as StudentResponseDTO
     */
    List<StudentResponseDTO> searchStudentsByFirstName(String firstName);

    /**
     * Search for students by their last name.
     * This method allows searching for students whose last name matches the given input.
     *
     * @param lastName The student's last name to search for
     * @return A list of students matching the last name, each as StudentResponseDTO
     */
    List<StudentResponseDTO> searchStudentsByLastName(String lastName);

    /**
     * Set the password for a student.
     * This method allows the admin to set a new password for a student.
     *
     * @param userName        The username of the student
     * @param userPasswordDTO DTO containing the new password for the student
     * @return ApiResponse indicating the result of the password update
     * @throws ResourceNotFoundException if the student is not found
     */
    @Transactional
    ApiResponse setStudentPassword(String userName, UserPasswordDTO userPasswordDTO) throws ResourceNotFoundException;
}
