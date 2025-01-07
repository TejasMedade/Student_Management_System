package com.synchrony.utils.generators;


import com.synchrony.models.Admin;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom ID generator for Admin entity.
 * This generator creates a unique admin ID using the prefix "ADM",
 * the date of creation, and a sequential random number starting from 0000.
 */
public class CustomAdminIdGenerator implements IdentifierGenerator {

    private static int counter = 0; // Static counter for sequential IDs

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if (object instanceof Admin admin) {
            // Use current date if createdDate is null
            Date creationDate = admin.getCreatedDate() != null
                    ? java.sql.Date.valueOf(admin.getCreatedDate())
                    : new Date();

            // Format the date part
            String dateOfCreation = new SimpleDateFormat("yyyyMMdd").format(creationDate);

            // Increment counter and format to 4 digits
            String randomNumber = String.format("%04d", counter++);

            // Generate custom ID
            return "ADM" + dateOfCreation + randomNumber;
        }
        throw new IllegalArgumentException("Object is not an Admin");
    }
}