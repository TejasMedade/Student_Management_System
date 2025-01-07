package com.synchrony.utils.generators;

import com.synchrony.models.Student;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Custom ID generator for Student entity.
 * This generator creates a unique student ID using the date of birth and a sequential random number starting from 0001.
 */
public class CustomStudentIdGenerator implements IdentifierGenerator {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if (object instanceof Student student) {
            // Use current date if dateOfBirth is null
            Date dob = student.getDateOfBirth() != null
                    ? java.sql.Date.valueOf(student.getDateOfBirth())
                    : new Date();

            // Format the date part
            String dateOfBirth = new SimpleDateFormat("yyyyMMdd").format(dob);

            // Increment counter and format to 4 digits
            String randomNumber = String.format("%04d", counter.getAndIncrement());

            // Generate custom ID
            return dateOfBirth + randomNumber;
        }
        throw new IllegalArgumentException("Object is not a Student");
    }
}