package com.codingbydivyanshu.yt.HospitalManagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class addDoctordto {
    private String name;
    private String email;
    private String specification;
}
