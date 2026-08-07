package com.codingbydivyanshu.yt.HospitalManagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Patientdto {
    private String name;
    private String email;
    private LocalDate DOB;
    private String gender;
}
