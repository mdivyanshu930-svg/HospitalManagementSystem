package com.codingbydivyanshu.yt.HospitalManagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class LoginRequestDto {
    private String username;
    private String password;
}
