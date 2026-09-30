package com.codingbydivyanshu.yt.HospitalManagement.Dto;

import com.codingbydivyanshu.yt.HospitalManagement.type.RoleType;
import jakarta.persistence.SecondaryTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignuprequestDto {
    private String username;
    private String name;
    private String password;
    Set<RoleType>roleTypes=new HashSet<>();
}
