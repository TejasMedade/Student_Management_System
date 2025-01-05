package com.synchrony.utils.generators;


import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

import com.synchrony.models.Admin;

import java.text.SimpleDateFormat;

/**
 * Custom ID generator for Admin entity.
 * This generator creates a unique admin ID using the prefix "ADM",
 * the date of creation, and a sequential random number starting from 0000.
 */
public class CustomAdminIdGenerator implements IdentifierGenerator {

    private static int counter = 0;  // Static counter for random number generation

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if (object instanceof Admin) {
            Admin admin = (Admin) object;

            // Use the date of creation (createdDate) to form the year part of the ID
            String dateOfCreation = new SimpleDateFormat("yyyyMMdd").format(admin.getCreatedDate());

            // Start with 0000 and increment each time an ID is generated
            String randomNumber = String.format("%04d", counter++);  // Format to 4 digits

            // Generate and return the custom admin ID with prefix
            return "ADM_" + dateOfCreation + "_" + randomNumber;
        }
        throw new IllegalArgumentException("Object is not an Admin");
    }
}
