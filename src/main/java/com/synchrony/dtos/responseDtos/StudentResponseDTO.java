package com.synchrony.dtos.responseDtos;


import com.synchrony.enums.ProfileStatus;
import com.synchrony.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private String firstName;
    private String lastName;
    private int age;
    private LocalDate dateOfBirth;
    private String contactNumber;
    private String bloodGroup;
    private String fatherName;
    private String motherName;
    private Address permanentAddress;
    private Address currentAddress;
    private String email;
    private String guardianName;
    private String guardianContact;
    private byte[] profilePhoto;
    private String rollNo;
    private String academicStream;
    private String academicCourse;
    private Year batchYear;
    private String classDivision;
    private ProfileStatus profileStatus;
    private LocalDate dateOfLeaving;
    private String remarks;
}
