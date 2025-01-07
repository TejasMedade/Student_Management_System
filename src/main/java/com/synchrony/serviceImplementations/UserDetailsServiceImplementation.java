package com.synchrony.serviceImplementations;

import com.synchrony.models.Admin;
import com.synchrony.models.Student;
import com.synchrony.repositories.AdminRepository;
import com.synchrony.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service implementation for loading user details during authentication.
 * <p>
 * This class is used by Spring Security to authenticate users. It checks if the username belongs to an
 * Admin or a Student, retrieves the user from the respective repository, and builds a UserDetails object.
 * </p>
 *
 * Author: @Tejas_Medade
 */
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;  // Repository to interact with Admin entities

    @Autowired
    private StudentRepository studentRepository;  // Repository to interact with Student entities

    /**
     * Loads the user by username during authentication.
     * This method checks if the username belongs to an Admin or a Student, fetches the corresponding user,
     * and returns the UserDetails with roles.
     *
     * @param username the username of the user to load
     * @return UserDetails containing the user's credentials and roles
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if the username belongs to an Admin (identifiable by "ADM" prefix)
        if (username.contains("ADM")) {
            Optional<Admin> admin = adminRepository.findById(username);
            if (admin.isPresent()) {
                admin.get().setLastLoginDate(LocalDate.now());
                // Return the UserDetails with "ADMIN" role if Admin is found
                return User.builder()
                        .username(admin.get().getUserName())
                        .password(admin.get().getPassword())
                        .roles("ADMIN")
                        .build();
            }
        } else {
            // Otherwise, treat the username as belonging to a Student
            Optional<Student> student = studentRepository.findById(username);
            if (student.isPresent()) {
                student.get().setLastLoginDate(LocalDate.now());
                // Return the UserDetails with "STUDENT" role if Student is found
                return User.builder()
                        .username(student.get().getUserName())
                        .password(student.get().getPassword())
                        .roles("STUDENT")
                        .build();
            }
        }
        // Throw exception if no user found with the given username
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
