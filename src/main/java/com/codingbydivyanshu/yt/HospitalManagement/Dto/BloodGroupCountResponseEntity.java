package com.codingbydivyanshu.yt.HospitalManagement.Dto;

import com.codingbydivyanshu.yt.HospitalManagement.type.Bloodgrouptype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BloodGroupCountResponseEntity {
    private Bloodgrouptype bloodgrouptype;
    private long count;

}
