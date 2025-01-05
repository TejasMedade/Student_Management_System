package com.synchrony.dtos.responseDtos;


import com.synchrony.enums.ProfileStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStudentResponseDTO {

    private String rollNo;
    private String academicStream;
    private String academicCourse;
    private Year batchYear;
    private String classDivision;
    private ProfileStatus profileStatus;
    private LocalDate dateOfLeaving;
    private String remarks;
}
