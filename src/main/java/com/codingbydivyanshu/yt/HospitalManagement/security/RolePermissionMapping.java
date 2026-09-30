package com.codingbydivyanshu.yt.HospitalManagement.security;

import com.codingbydivyanshu.yt.HospitalManagement.type.PermissionType;
import com.codingbydivyanshu.yt.HospitalManagement.type.RoleType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codingbydivyanshu.yt.HospitalManagement.type.PermissionType.*;
import static com.codingbydivyanshu.yt.HospitalManagement.type.RoleType.*;

public class RolePermissionMapping {
    private static final Map<RoleType, Set<PermissionType>> map=Map.of(PATIENT,Set.of(PATIENT_READ,APPOINTMENT_READ,APPOINTMENT_WRITE),
            DOCTOR,Set.of(APPOINTMENT_READ,APPOINTMENT_WRITE,APPOINTMENT_DELETE,PATIENT_READ),
            ADMIN,Set.of(APPOINTMENT_READ,APPOINTMENT_WRITE,APPOINTMENT_DELETE,
                    PATIENT_READ,PATIENT_WRITE,USER_MANAGE,REPORT_VIEW)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(RoleType roleType) {
        return map.get(roleType).stream().map(permissionType ->
                new SimpleGrantedAuthority(permissionType.getPermission())).collect(Collectors.toSet());

    }
}