package com.synchrony.repositories;

import com.synchrony.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {


    List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByLastNameContaining(String lastName);
}
