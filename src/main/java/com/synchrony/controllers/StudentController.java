/**
 * StudentController is a REST controller responsible for handling student-related actions such as
 * updating details, managing profile pictures, viewing data, and changing passwords.
 *
 * @author Tejas_Medade
 */
package com.synchrony.controllers;

import com.synchrony.dtos.requestDtos.StudentRequestDTO;
import com.synchrony.dtos.requestDtos.UserPasswordDTO;
import com.synchrony.dtos.responseDtos.StudentResponseDTO;
import com.synchrony.exceptions.ResourceNotFoundException;
import com.synchrony.services.StudentService;
import com.synchrony.utils.responseHandlers.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The StudentController class handles actions related to student accounts,
 * including updating student details, managing profile pictures, viewing student data,
 * and changing student passwords.
 */
@RestController
@RequestMapping("/synchrony/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Edit student details. Only the modified fields from the DTO will be updated in the entity.
     *
     * @param userName       The unique username of the student to be edited
     * @param studentDTO     The DTO containing the updated student details
     * @return               The updated student details as a StudentResponseDTO
     * @throws ResourceNotFoundException If student is not found
     */
    @PutMapping("/{userName}")
    public ResponseEntity<StudentResponseDTO> editStudentDetails(@PathVariable String userName,
                                                                 @RequestBody StudentRequestDTO studentDTO) throws ResourceNotFoundException {
        StudentResponseDTO updatedStudent = studentService.editStudentDetails(userName, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * Upload a new profile picture for the student. If a new picture is uploaded, it replaces the old one.
     *
     * @param userName       The unique username of the student whose profile picture is to be updated
     * @param file           The new profile picture file to be uploaded
     * @return               Response entity indicating success or failure
     * @throws IOException   If there is an issue with reading or writing the file
     * @throws ResourceNotFoundException If student is not found
     */
    @PostMapping("/{userName}/profile-picture")
    public ResponseEntity<Void> uploadProfilePicture(@PathVariable String userName,
                                                     @RequestParam("file") MultipartFile file) throws IOException, ResourceNotFoundException {
        studentService.uploadProfilePicture(userName, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Delete the student's current profile picture. If no profile picture exists, it will revert to a default picture.
     *
     * @param userName       The unique username of the student whose profile picture is to be deleted
     * @return               Response entity indicating success or failure
     * @throws IOException   If there is an issue with reading or writing the file
     * @throws ResourceNotFoundException If student is not found
     */
    @DeleteMapping("/{userName}/profile-picture")
    public ResponseEntity<Void> deleteProfilePicture(@PathVariable String userName) throws IOException, ResourceNotFoundException {
        studentService.deleteProfilePicture(userName);
        return ResponseEntity.noContent().build();
    }

    /**
     * View the currently authenticated student's data.
     *
     * @param userName       The unique username of the authenticated student
     * @return               The student's details as a StudentResponseDTO
     * @throws ResourceNotFoundException If student is not found
     */
    @GetMapping("/{userName}")
    public ResponseEntity<StudentResponseDTO> viewMyData(@PathVariable String userName) throws ResourceNotFoundException {
        StudentResponseDTO studentData = studentService.viewMyData(userName);
        return ResponseEntity.ok(studentData);
    }

    /**
     * Change the student's password.
     *
     * @param userName           The unique username of the student
     * @param oldPassword       The current password of the student
     * @param userPasswordDTO   The new password and any other required data
     * @return                  API response with status and message
     * @throws ResourceNotFoundException If student is not found
     */
    @PutMapping("/{userName}/password")
    public ResponseEntity<ApiResponse> changeStudentPassword(@PathVariable String userName,
                                                             @RequestParam("oldPassword") String oldPassword,
                                                             @RequestBody UserPasswordDTO userPasswordDTO) throws ResourceNotFoundException {
        ApiResponse response = studentService.changeStudentPassword(userName, oldPassword, userPasswordDTO);
        return ResponseEntity.ok(response);
    }
}
