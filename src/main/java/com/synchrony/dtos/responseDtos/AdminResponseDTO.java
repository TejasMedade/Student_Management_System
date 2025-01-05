package com.synchrony.dtos.responseDtos;


import com.synchrony.enums.ProfileStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private ProfileStatus profileStatus;
    private byte[] profilePhoto;
    private LocalDate createdDate;
    private LocalDate lastLoginDate;
    private LocalDate modifiedDate;

}
