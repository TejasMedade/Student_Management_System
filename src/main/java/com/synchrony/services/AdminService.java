package com.synchrony.services;


import com.synchrony.dtos.requestDtos.AdminRequestDTO;
import com.synchrony.dtos.requestDtos.StudentRequestDTO;
import com.synchrony.dtos.responseDtos.AdminResponseDTO;
import com.synchrony.dtos.responseDtos.StudentResponseDTO;
import com.synchrony.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    /**
     * Get all students in the system.
     *
     * @return List of StudentResponseDTO representing all students
     */
    List<StudentResponseDTO> getAllStudents();

    /**
     * Add a new student account.
     *
     * @param studentRequestDTO DTO containing student details
     * @return StudentResponseDTO with the newly created student details
     */

    @Transactional
    StudentResponseDTO addStudentAccount(StudentRequestDTO studentRequestDTO, MultipartFile file) throws IOException;

    /**
     * Delete a student account by user name.
     *
     * @param userName Unique identifier for the student
     */
    void deleteStudentAccount(String userName) throws ResourceNotFoundException;

    /**
     * Edit admin account details.
     *
     * @param adminRequestDTO DTO containing updated admin account details
     * @return Updated admin account details
     */
    @Transactional
    AdminResponseDTO editAdminAccount(String userName, AdminRequestDTO adminRequestDTO, MultipartFile file) throws IOException, ResourceNotFoundException;

    /**
     * Create a new admin account.
     *
     * @param adminRequestDTO DTO containing admin account details
     * @return Created admin account details
     */
    @Transactional
    AdminResponseDTO createAdminAccount(AdminRequestDTO adminRequestDTO, MultipartFile file) throws IOException;

    /**
     * Delete an admin account by admin username.
     *
     * @param adminUsername Admin's username to be deleted
     */
    void deleteAdminAccount(String adminUsername) throws ResourceNotFoundException;


    /**
     * Get the details of a student by their unique username.
     *
     * @param userName       The unique username of the student
     * @return               The student's details as a StudentResponseDTO
     */
    StudentResponseDTO getStudentDetailsById(String userName) throws ResourceNotFoundException;

    /**
     * Search for students by their first name.
     *
     * @param firstName      The student's first name to search for
     * @return               A list of students matching the first name, each as StudentResponseDTO
     */
    List<StudentResponseDTO> searchStudentsByFirstName(String firstName);

    /**
     * Search for students by their last name.
     *
     * @param lastName       The student's last name to search for
     * @return               A list of students matching the last name, each as StudentResponseDTO
     */
    List<StudentResponseDTO> searchStudentsByLastName(String lastName);
}
