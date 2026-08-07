package com.codingbydivyanshu.yt.HospitalManagement.Dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Doctordto {
    private String name;
    private String specilization;
}
