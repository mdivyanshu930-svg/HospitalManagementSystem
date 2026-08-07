package com.codingbydivyanshu.yt.HospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.MethodDelegationBinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 100)
    private String name;
    @Column(length =100)
    private String specilization;
    @Column(nullable = false, unique = true,length = 100)
    private String email;

    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments=new HashSet<>();
    @OneToMany(mappedBy = "doctor")
    private List<Appointment>appointments=new ArrayList<>();
}
