package com.synchrony.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
@Getter
@Setter
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


    public String getAddressLine_1() {
        return addressLine_1;
    }

    public void setAddressLine_1(String addressLine_1) {
        this.addressLine_1 = addressLine_1;
    }

    public String getAddressLine_2() {
        return addressLine_2;
    }

    public void setAddressLine_2(String addressLine_2) {
        this.addressLine_2 = addressLine_2;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
