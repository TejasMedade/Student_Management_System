package com.synchrony.controllers;


import com.synchrony.dtos.requestDtos.AdminRequestDTO;
import com.synchrony.dtos.requestDtos.StudentRequestDTO;
import com.synchrony.dtos.responseDtos.AdminResponseDTO;
import com.synchrony.dtos.responseDtos.StudentResponseDTO;
import com.synchrony.exceptions.ResourceNotFoundException;
import com.synchrony.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * REST Controller for handling administrative actions such as managing students and admins.
 * Provides endpoints for CRUD operations on students and admin accounts.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Get all students in the system.
     *
     * @return List of StudentResponseDTO representing all students
     */
    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> students = adminService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * Add a new student account.
     *
     * @param studentRequestDTO DTO containing student details
     * @param file             Optional profile photo
     * @return StudentResponseDTO with the newly created student details
     * @throws IOException If file upload fails
     */
    @PostMapping("/students")
    public ResponseEntity<StudentResponseDTO> addStudentAccount(@RequestBody StudentRequestDTO studentRequestDTO,
                                                                @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        StudentResponseDTO createdStudent = adminService.addStudentAccount(studentRequestDTO, file);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    /**
     * Delete a student account by user name.
     *
     * @param userName The unique identifier for the student
     * @return Response entity indicating success or failure
     * @throws ResourceNotFoundException If student is not found
     */
    @DeleteMapping("/students/{userName}")
    public ResponseEntity<Void> deleteStudentAccount(@PathVariable String userName) throws ResourceNotFoundException {
        adminService.deleteStudentAccount(userName);
        return ResponseEntity.noContent().build();
    }

    /**
     * Edit admin account details.
     *
     * @param userName         Admin's username
     * @param adminRequestDTO  DTO containing updated admin account details
     * @param file             Optional profile photo
     * @return AdminResponseDTO with updated admin details
     * @throws ResourceNotFoundException If admin is not found
     * @throws IOException If file upload fails
     */
    @PutMapping("/admins/{userName}")
    public ResponseEntity<AdminResponseDTO> editAdminAccount(@PathVariable String userName,
                                                             @RequestBody AdminRequestDTO adminRequestDTO,
                                                             @RequestParam(value = "file", required = false) MultipartFile file) throws IOException, ResourceNotFoundException {
        AdminResponseDTO updatedAdmin = adminService.editAdminAccount(userName, adminRequestDTO, file);
        return ResponseEntity.ok(updatedAdmin);
    }

    /**
     * Create a new admin account.
     *
     * @param adminRequestDTO DTO containing admin account details
     * @param file            Optional profile photo
     * @return AdminResponseDTO with newly created admin details
     * @throws IOException If file upload fails
     */
    @PostMapping("/admins")
    public ResponseEntity<AdminResponseDTO> createAdminAccount(@RequestBody AdminRequestDTO adminRequestDTO,
                                                               @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        AdminResponseDTO createdAdmin = adminService.createAdminAccount(adminRequestDTO, file);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    /**
     * Delete an admin account by username.
     *
     * @param adminUsername The admin's username to be deleted
     * @return Response entity indicating success or failure
     * @throws ResourceNotFoundException If admin is not found
     */
    @DeleteMapping("/admins/{adminUsername}")
    public ResponseEntity<Void> deleteAdminAccount(@PathVariable String adminUsername) throws ResourceNotFoundException {
        adminService.deleteAdminAccount(adminUsername);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get the details of a student by their unique username.
     *
     * @param userName The unique username of the student
     * @return StudentResponseDTO with the student's details
     * @throws ResourceNotFoundException If student is not found
     */
    @GetMapping("/students/{userName}")
    public ResponseEntity<StudentResponseDTO> getStudentDetailsById(@PathVariable String userName) throws ResourceNotFoundException {
        StudentResponseDTO studentDetails = adminService.getStudentDetailsById(userName);
        return ResponseEntity.ok(studentDetails);
    }

    /**
     * Search for students by their first name.
     *
     * @param firstName The student's first name to search for
     * @return List of students matching the first name
     */
    @GetMapping("/students/search/first-name/{firstName}")
    public ResponseEntity<List<StudentResponseDTO>> searchStudentsByFirstName(@PathVariable String firstName) {
        List<StudentResponseDTO> students = adminService.searchStudentsByFirstName(firstName);
        return ResponseEntity.ok(students);
    }

    /**
     * Search for students by their last name.
     *
     * @param lastName The student's last name to search for
     * @return List of students matching the last name
     */
    @GetMapping("/students/search/last-name/{lastName}")
    public ResponseEntity<List<StudentResponseDTO>> searchStudentsByLastName(@PathVariable String lastName) {
        List<StudentResponseDTO> students = adminService.searchStudentsByLastName(lastName);
        return ResponseEntity.ok(students);
    }
}
