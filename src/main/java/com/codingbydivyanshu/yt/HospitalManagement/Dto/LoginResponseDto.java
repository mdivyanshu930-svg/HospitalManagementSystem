package com.codingbydivyanshu.yt.HospitalManagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDto {
    private String jwt;
    Long userid;
}
