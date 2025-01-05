package com.synchrony.serviceImplementations;

import com.synchrony.dtos.requestDtos.StudentRequestDTO;
import com.synchrony.dtos.requestDtos.UserPasswordDTO;
import com.synchrony.dtos.responseDtos.StudentResponseDTO;
import com.synchrony.models.Student;
import com.synchrony.repositories.StudentRepository;
import com.synchrony.services.StudentService;
import com.synchrony.utils.responseHandlers.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.synchrony.exceptions.ResourceNotFoundException;

/**
 * Service implementation for handling student-related operations.
 * <p>
 * This class contains the business logic for editing student details, uploading and deleting profile pictures,
 * and searching and viewing student data. It interacts with the Student repository to fetch and save data.
 * </p>
 *
 * Author: @Tejas_Medade
 */
@Service
public class StudentServiceImplementation implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String DEFAULT_PROFILE_PIC_PATH = "src/main/resources/static/images/default-profile-picture.jpg";  // Default profile picture path

    /**
     * Edit student details. Only the edited fields will be updated.
     *
     * @param userName  Student's unique username
     * @param studentDTO DTO containing updated student details
     * @return Updated student details as StudentResponseDTO
     * @throws ResourceNotFoundException if the student is not found
     */
    @Transactional
    @Override
    public StudentResponseDTO editStudentDetails(String userName, StudentRequestDTO studentDTO) throws ResourceNotFoundException {
        // Fetch the student by userName, throw exception if not found
        Student student = studentRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "userName", userName));

        // Map updated details from DTO
        modelMapper.map(studentDTO, student);
        student.setModifiedDate(java.time.LocalDate.now());  // Set the modified date

        // Save updated student details
        studentRepository.save(student);

        // Return updated student details as DTO
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    /**
     * Upload profile picture for the student.
     *
     * @param userName Student's unique username
     * @param file     Profile picture file
     * @throws IOException If file processing fails
     * @throws ResourceNotFoundException if the student is not found
     */
    @Transactional
    @Override
    public void uploadProfilePicture(String userName, MultipartFile file) throws IOException, ResourceNotFoundException {
        // Fetch the student by userName, throw exception if not found
        Student student = studentRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "userName", userName));

        // Save the profile picture (can be saved in a database or as a byte array)
        student.setProfilePhoto(file.getBytes());
        studentRepository.save(student);
    }

    /**
     * Delete profile picture and set default picture if none exists.
     *
     * @param userName Student's unique username
     * @throws IOException If file processing fails
     * @throws ResourceNotFoundException if the student is not found
     */
    @Transactional
    @Override
    public void deleteProfilePicture(String userName) throws IOException, ResourceNotFoundException {
        // Fetch the student by userName, throw exception if not found
        Student student = studentRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "userName", userName));

        if (student.getProfilePhoto() == null) {
            // If no profile photo exists, load the default one
            byte[] defaultImage = Files.readAllBytes(Paths.get(DEFAULT_PROFILE_PIC_PATH));
            student.setProfilePhoto(defaultImage);
        } else {
            // Delete the profile photo (or keep it empty)
            student.setProfilePhoto(null);
        }

        // Save updated student with the new profile picture
        studentRepository.save(student);
    }

    /**
     * View the current student's data.
     *
     * @param userName Student's unique username
     * @return Current student data as StudentResponseDTO
     */
    @Override
    public StudentResponseDTO viewMyData(String userName) throws ResourceNotFoundException {
        // Fetch the student by userName, throw exception if not found
        Student student = studentRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "userName", userName));

        return modelMapper.map(student, StudentResponseDTO.class);
    }



    /**
     * Change the password of the student.
     *
     * @param userName the username of the student.
     * @param oldPassword the current password.
     * @param userPasswordDTO the new password.
     * @return ApiResponse with status and message.
     * @throws ResourceNotFoundException if student is not found.
     */
    @Transactional
    @Override
    public ApiResponse changeStudentPassword(String userName, String oldPassword, UserPasswordDTO userPasswordDTO) throws ResourceNotFoundException {
        // Fetch student from database
        Student student = studentRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "userName", userName));

        // Verify old password
        if (!passwordEncoder.matches(oldPassword, student.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }

        // Encrypt new password and save
        student.setPassword(passwordEncoder.encode(userPasswordDTO.getPassword()));
        studentRepository.save(student);

        // Return API response
        return new ApiResponse(LocalDateTime.now(), "Password changed successfully", true);
    }
}