package com.synchrony.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents an Address entity, which can be embedded within other entities.
 * It stores the address details like street address, city, district, state, and postal code.
 *
 * Author: @Tejas_Medade
 *
 * This class is annotated with @Embeddable, meaning it can be embedded as part of another entity's attributes.
 * Example usage: It can be used to store permanent or current address of a student in a student management system.
 */
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    /**
     * The first line of the address (e.g., house number, street name).
     */
    @NotNull(message = "Address Line 1 cannot be null")
    @NotEmpty(message = "Address Line 1 cannot be empty")
    @Size(max = 255, message = "Address Line 1 cannot exceed 255 characters")
    private String addressLine_1;

    /**
     * The second line of the address (e.g., apartment, suite, or additional address details).
     */
    @NotNull(message = "Address Line 2 cannot be null")
    @NotEmpty(message = "Address Line 2 cannot be empty")
    @Size(max = 255, message = "Address Line 2 cannot exceed 255 characters")
    private String addressLine_2;

    /**
     * A landmark near the address (e.g., "Near Central Park").
     */
    @NotNull(message = "Landmark cannot be null")
    @NotEmpty(message = "Landmark cannot be empty")
    @Size(max = 25, message = "Landmark cannot exceed 100 characters")
    private String landMark;

    /**
     * The city where the address is located.
     */
    @NotNull(message = "City cannot be null")
    @NotEmpty(message = "City cannot be empty")
    @Size(max = 15, message = "City cannot exceed 100 characters")
    private String city;

    /**
     * The district or region where the address is located.
     */
    @NotNull(message = "District cannot be null")
    @NotEmpty(message = "District cannot be empty")
    @Size(max = 15, message = "District cannot exceed 100 characters")
    private String district;

    /**
     * The state where the address is located.
     */
    @NotNull(message = "State cannot be null")
    @NotEmpty(message = "State cannot be empty")
    @Size(max = 15, message = "State cannot exceed 100 characters")
    private String state;

    /**
     * The postal code or zip code for the address.
     */
    @NotNull(message = "Zip Code cannot be null")
    @NotEmpty(message = "Zip Code cannot be empty")
    @Size(max = 6, message = "Zip Code cannot exceed 20 characters")
    private String zipCode;
}
