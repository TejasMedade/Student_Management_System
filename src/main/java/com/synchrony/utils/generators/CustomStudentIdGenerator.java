package com.synchrony.utils.generators;

import com.synchrony.models.Student;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

import java.text.SimpleDateFormat;

/**
 * Custom ID generator for Student entity.
 * This generator creates a unique student ID using the date of birth and a sequential random number starting from 0001.
 */
public class CustomStudentIdGenerator implements IdentifierGenerator {

    private static int counter = 1;  // Static counter for random number generation

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if (object instanceof Student) {
            Student student = (Student) object;

            // Use the date of birth (dateOfBirth) to form the year part of the ID
            String dateOfBirth = new SimpleDateFormat("yyyyMMdd").format(student.getDateOfBirth());

            // Start with 0001 and increment each time an ID is generated
            String randomNumber = String.format("%04d", counter++);  // Format to 4 digits

            // Generate and return the custom student ID without prefix
            return dateOfBirth + "_" + randomNumber;
        }
        throw new IllegalArgumentException("Object is not a Student");
    }
}
