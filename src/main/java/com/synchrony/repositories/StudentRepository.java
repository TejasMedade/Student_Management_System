package com.synchrony.repositories;

import com.synchrony.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for interacting with the Student entity.
 * Extends JpaRepository to provide CRUD operations and custom query methods for Student entities.
 *
 * @author Tejas_Medade
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    /**
     * Find students whose first name contains the given substring.
     *
     * @param firstName The substring to search for in the first name
     * @return A list of students whose first name contains the provided substring
     */
    List<Student> findByFirstNameContaining(String firstName);

    /**
     * Find students whose last name contains the given substring.
     *
     * @param lastName The substring to search for in the last name
     * @return A list of students whose last name contains the provided substring
     */
    List<Student> findByLastNameContaining(String lastName);
}
