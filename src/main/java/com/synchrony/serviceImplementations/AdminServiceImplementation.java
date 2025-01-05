package com.synchrony.serviceImplementations;

import com.synchrony.dtos.requestDtos.AdminRequestDTO;
import com.synchrony.dtos.requestDtos.AdminStudentRequestDTO;
import com.synchrony.dtos.requestDtos.StudentRequestDTO;
import com.synchrony.dtos.requestDtos.UserPasswordDTO;
import com.synchrony.dtos.responseDtos.AdminResponseDTO;
import com.synchrony.dtos.responseDtos.StudentResponseDTO;
import com.synchrony.exceptions.ResourceNotFoundException;
import com.synchrony.models.Admin;
import com.synchrony.models.Student;
import com.synchrony.repositories.AdminRepository;
import com.synchrony.repositories.StudentRepository;
import com.synchrony.services.AdminService;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of AdminService interface that manages administrative actions.
 * This service includes operations like managing students and admin accounts.
 */
@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String DEFAULT_PROFILE_PIC_PATH = "src/main/resources/static/images/default-profile-picture.jpg";

    /**
     * Get all students in the system.
     *
     * @return List of StudentResponseDTO representing all students
     */
    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }


    /**
     * Add a new student account.
     * If no profile picture is provided, sets the default profile picture.
     *
     * @param studentRequestDTO DTO containing student details
     * @param file              MultipartFile representing the profile picture
     * @return StudentResponseDTO with the newly created student details
     */
    @Transactional
    @Override
    public StudentResponseDTO addStudentAccount(StudentRequestDTO studentRequestDTO, MultipartFile file) throws IOException {
        Student student = modelMapper.map(studentRequestDTO, Student.class);
        student.setCreatedDate(LocalDate.now());
        // Handle profile picture
        if (file != null && !file.isEmpty()) {
            student.setProfilePhoto(file.getBytes());
        } else {
            byte[] defaultImage = Files.readAllBytes(Paths.get(DEFAULT_PROFILE_PIC_PATH));
            student.setProfilePhoto(defaultImage);
        }

        // Save student details in the database
        studentRepository.save(student);
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    /**
     * Delete a student account by username.
     *
     * @param userName Unique identifier for the student
     */
    @Override
    @Transactional
    public void deleteStudentAccount(String userName) throws ResourceNotFoundException {
        Student student = studentRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "userName", userName));
        studentRepository.delete(student);
    }


    /**
     * Admin method to update basic student details like name, contact info, etc.
     *
     * @param userName the username of the student.
     * @param studentRequestDTO the new student details.
     * @param file optional profile photo file.
     * @return the updated student information.
     * @throws IOException if file upload fails.
     * @throws ResourceNotFoundException if student is not found.
     */
    @Transactional
    public StudentResponseDTO updateBasicStudentDetails(String userName, StudentRequestDTO studentRequestDTO, MultipartFile file) throws IOException, ResourceNotFoundException {
        // Fetch student from database
        Student student = studentRepository.findById(userName)
                .orElseThrow(() ->new ResourceNotFoundException("Student", "userName", userName));

        // Use ModelMapper to map the basic details from DTO to entity
        modelMapper.map(studentRequestDTO, student);

        // Update profile photo if present
        if (file != null && !file.isEmpty()) {
            student.setProfilePhoto(file.getBytes());
        }

        studentRepository.save(student);

        // Return updated student info as response
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    /**
     * Admin-only method to update non-editable student details such as academic stream, batch year, etc.
     *
     * @param userName the username of the student.
     * @param adminStudentRequestDTO the updated non-editable details.
     * @return the updated student information.
     * @throws ResourceNotFoundException if student is not found.
     */
    @Transactional
    public StudentResponseDTO updateAdminOnlyStudentDetails(String userName, AdminStudentRequestDTO adminStudentRequestDTO) throws ResourceNotFoundException {
        // Fetch student from database
        Student student = studentRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "userName", userName));

        // Use ModelMapper to map the admin-only details from DTO to entity
        modelMapper.map(adminStudentRequestDTO, student);

        studentRepository.save(student);

        // Return updated student info as response
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    /**
     * Create a new admin account.
     * If no profile picture is provided, sets the default profile picture.
     *
     * @param adminRequestDTO DTO containing admin account details
     * @param file            MultipartFile representing the profile picture
     * @return Created admin account details
     */
    @Transactional
    @Override
    public AdminResponseDTO createAdminAccount(AdminRequestDTO adminRequestDTO, MultipartFile file) throws IOException {
        Admin admin = modelMapper.map(adminRequestDTO, Admin.class);
        admin.setCreatedDate(LocalDate.now());
        // Handle profile picture
        if (file != null && !file.isEmpty()) {
            admin.setProfilePhoto(file.getBytes());
        } else {
            byte[] defaultImage = Files.readAllBytes(Paths.get(DEFAULT_PROFILE_PIC_PATH));
            admin.setProfilePhoto(defaultImage);
        }

        // Save new admin details in the database
        adminRepository.save(admin);
        return modelMapper.map(admin, AdminResponseDTO.class);
    }

    /**
     * Edit admin account details.
     * If a new profile picture is provided, it updates it.
     *
     * @param adminRequestDTO DTO containing updated admin account details
     * @param file            MultipartFile representing the new profile picture (optional)
     * @return Updated admin account details
     */
    @Transactional
    @Override
    public AdminResponseDTO editAdminAccount(String userName, AdminRequestDTO adminRequestDTO, MultipartFile file) throws IOException, ResourceNotFoundException {
        Admin admin = adminRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "username", userName));

        // Update admin details from DTO
        modelMapper.map(adminRequestDTO, admin);

        // Handle profile picture update
        if (file != null && !file.isEmpty()) {
            admin.setProfilePhoto(file.getBytes());
        }

        adminRepository.save(admin);

        return modelMapper.map(admin, AdminResponseDTO.class);
    }

    /**
     * Delete an admin account by admin username.
     *
     * @param adminUsername Admin's username to be deleted
     */
    @Override
    @Transactional
    public void deleteAdminAccount(String adminUsername) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(adminUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "username", adminUsername));
        adminRepository.delete(admin);
    }

    /**
     * Upload a profile picture for the admin.
     *
     * @param adminUsername Admin's unique username
     * @param file          MultipartFile representing the profile picture
     * @throws IOException If file processing fails
     */
    @Transactional
    public void uploadProfilePictureForAdmin(String adminUsername, MultipartFile file) throws IOException, ResourceNotFoundException {
        Admin admin = adminRepository.findById(adminUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "username", adminUsername));

        admin.setProfilePhoto(file.getBytes());
        adminRepository.save(admin);
    }

    /**
     * Change the password of the admin.
     *
     * @param userName the username of the admin.
     * @param oldPassword the current password.
     * @param userPasswordDTO the new password.
     * @return ApiResponse with status and message.
     * @throws ResourceNotFoundException if admin is not found.
     */
    @Transactional
    @Override
    public ApiResponse changeAdminPassword(String userName, String oldPassword, UserPasswordDTO userPasswordDTO) throws ResourceNotFoundException {
        // Fetch admin from database
        Admin admin = adminRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "userName", userName));

        // Verify old password
        if (!passwordEncoder.matches(oldPassword, admin.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }

        // Encrypt new password and save
        admin.setPassword(passwordEncoder.encode(userPasswordDTO.getPassword()));
        adminRepository.save(admin);

        // Return API response
        return new ApiResponse(LocalDateTime.now(), "Admin password changed successfully", true);
    }

    /**
     * Get student details by ID.
     *
     * @param userName Student's unique username
     * @return Student details as StudentResponseDTO
     * @throws ResourceNotFoundException if the student is not found
     */
    @Override
    public StudentResponseDTO getStudentDetailsById(String userName) throws ResourceNotFoundException {
        // Fetch the student by userName, throw exception if not found
        Student student = studentRepository.findById(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "userName", userName));

        return modelMapper.map(student, StudentResponseDTO.class);
    }

    /**
     * Search for students by their first name.
     *
     * @param firstName Student's first name
     * @return List of matching students as StudentResponseDTO
     */
    @Override
    public List<StudentResponseDTO> searchStudentsByFirstName(String firstName) {
        // Fetch students by first name
        List<Student> students = studentRepository.findByFirstNameContaining(firstName);

        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Search for students by their last name.
     *
     * @param lastName Student's last name
     * @return List of matching students as StudentResponseDTO
     */
    @Override
    public List<StudentResponseDTO> searchStudentsByLastName(String lastName) {
        // Fetch students by last name
        List<Student> students = studentRepository.findByLastNameContaining(lastName);

        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }



}