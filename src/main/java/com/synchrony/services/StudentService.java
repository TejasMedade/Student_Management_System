package com.synchrony.services;

import com.synchrony.dtos.requestDtos.StudentRequestDTO;
import com.synchrony.dtos.requestDtos.UserPasswordDTO;
import com.synchrony.dtos.responseDtos.StudentResponseDTO;
import com.synchrony.exceptions.ResourceNotFoundException;
import com.synchrony.utils.responseHandlers.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service interface for handling student-related operations.
 * <p>
 * This interface defines methods for managing student details, such as editing student data,
 * uploading or deleting profile pictures, viewing student data, and changing passwords.
 * </p>
 *
 * Author: @Tejas_Medade
 */
public interface StudentService {

    /**
     * Edit student details. Only the modified fields from the DTO will be updated in the entity.
     * This method updates the student details based on the provided data.
     *
     * @param userName   The unique username of the student to be edited
     * @param studentDTO The DTO containing the updated student details
     * @return           The updated student details as a StudentResponseDTO
     * @throws ResourceNotFoundException if the student is not found
     */
    @Transactional
    StudentResponseDTO editStudentDetails(String userName, StudentRequestDTO studentDTO) throws ResourceNotFoundException;

    /**
     * Upload a new profile picture for the student.
     * If a new picture is uploaded, it replaces the old one in the system.
     * This method handles the file upload and saves the student's new profile picture.
     *
     * @param userName The unique username of the student whose profile picture is to be updated
     * @param file     The new profile picture file to be uploaded
     * @throws IOException           If there is an issue with reading or writing the file
     * @throws ResourceNotFoundException if the student is not found
     */
    @Transactional
    void uploadProfilePicture(String userName, MultipartFile file) throws IOException, ResourceNotFoundException;

    /**
     * Delete the student's current profile picture.
     * If no profile picture exists, the system will revert to a default picture.
     * This method either deletes the profile picture or sets the default picture.
     *
     * @param userName The unique username of the student whose profile picture is to be deleted
     * @throws IOException           If there is an issue with reading or writing the file
     * @throws ResourceNotFoundException if the student is not found
     */
    @Transactional
    void deleteProfilePicture(String userName) throws IOException, ResourceNotFoundException;

    /**
     * View the currently authenticated student's data.
     * This method retrieves and returns the details of the student based on their username.
     *
     * @param userName The unique username of the authenticated student
     * @return         The student's details as a StudentResponseDTO
     * @throws ResourceNotFoundException if the student is not found
     */
    StudentResponseDTO viewMyData(String userName) throws ResourceNotFoundException;

    /**
     * Change the password of the student.
     * This method verifies the old password and updates it with the new password.
     *
     * @param userName        The username of the student
     * @param oldPassword     The current password
     * @param userPasswordDTO The new password details
     * @return                An ApiResponse indicating the result of the password change operation
     * @throws ResourceNotFoundException if the student is not found
     */
    @Transactional
    ApiResponse changeStudentPassword(String userName, String oldPassword, UserPasswordDTO userPasswordDTO) throws ResourceNotFoundException;
}
