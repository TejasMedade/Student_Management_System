package com.synchrony.configurations.DataLoaders;

import com.synchrony.enums.ProfileStatus;
import com.synchrony.models.Admin;
import com.synchrony.models.Student;
import com.synchrony.repositories.AdminRepository;
import com.synchrony.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    /**
     * Inserts default admin and student accounts into the database.
     *
     * @param adminRepository  Repository for saving admin users.
     * @param studentRepository Repository for saving student users.
     * @param passwordEncoder   Encoder for hashing passwords.
     * @return CommandLineRunner that will execute on application startup.
     */
    @Bean
    public CommandLineRunner loadDefaultUsers(AdminRepository adminRepository,
                                              StudentRepository studentRepository,
                                              PasswordEncoder passwordEncoder) {
        return args -> {
            // Create default admin account if not already exists

                Admin defaultAdmin = new Admin();
                defaultAdmin.setFirstName("Default");
                defaultAdmin.setLastName("Admin");
                defaultAdmin.setEmail("admin@example.com");
                defaultAdmin.setContactNumber("1234567890");
                defaultAdmin.setPassword(passwordEncoder.encode("Tejas@1998"));
                defaultAdmin.setCreatedDate(LocalDate.now());
                defaultAdmin.setModifiedDate(LocalDate.now());
                defaultAdmin.setStatus(ProfileStatus.ACTIVE);
                adminRepository.save(defaultAdmin);


            // Create default student account if not already exists

                Student defaultStudent = new Student();
                defaultStudent.setFirstName("Default");
                defaultStudent.setLastName("Student");
                defaultStudent.setEmail("student@example.com");
                defaultStudent.setContactNumber("0987654321");
                defaultStudent.setPassword(passwordEncoder.encode("Student@123"));
                defaultStudent.setDateOfBirth(LocalDate.of(2000, 1, 1));
                defaultStudent.setDateOfRegistration(LocalDate.now());
                defaultStudent.setProfileStatus(ProfileStatus.ACTIVE);
                defaultStudent.setCreatedDate(LocalDate.now());
                defaultStudent.setModifiedDate(LocalDate.now());
                studentRepository.save(defaultStudent);

        };
    }
}
