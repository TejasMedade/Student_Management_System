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
import java.util.List;

@Service
public interface StudentService {

    /**
     * Edit student details. Only the modified fields from the DTO will be updated in the entity.
     *
     * @param userName       The unique username of the student to be edited
     * @param studentDTO     The DTO containing the updated student details
     * @return              The updated student details as a StudentResponseDTO
     */
    @Transactional
    StudentResponseDTO editStudentDetails(String userName, StudentRequestDTO studentDTO) throws ResourceNotFoundException;

    /**
     * Upload a new profile picture for the student.
     * If a new picture is uploaded, it replaces the old one in the system.
     *
     * @param userName       The unique username of the student whose profile picture is to be updated
     * @param file           The new profile picture file to be uploaded
     * @throws IOException   If there is an issue with reading or writing the file
     */
    @Transactional
    void uploadProfilePicture(String userName, MultipartFile file) throws IOException, ResourceNotFoundException;

    /**
     * Delete the student's current profile picture.
     * If no profile picture exists, the system will revert to a default picture.
     *
     * @param userName       The unique username of the student whose profile picture is to be deleted
     * @throws IOException   If there is an issue with reading or writing the file
     */
    @Transactional
    void deleteProfilePicture(String userName) throws IOException, ResourceNotFoundException;

    /**
     * View the currently authenticated student's data.
     *
     * @param userName       The unique username of the authenticated student
     * @return               The student's details as a StudentResponseDTO
     */
    StudentResponseDTO viewMyData(String userName) throws ResourceNotFoundException;

    @Transactional
    ApiResponse changeStudentPassword(String userName, String oldPassword, UserPasswordDTO userPasswordDTO) throws ResourceNotFoundException;
}
