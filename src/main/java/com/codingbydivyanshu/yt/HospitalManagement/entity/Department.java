package com.codingbydivyanshu.yt.HospitalManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable = false,unique = true,length = 100)
    private String name;
    @OneToOne
    private Doctor headdoctor;
    @ManyToMany
    @JoinTable(
            name="my_dept_doctor",
            joinColumns=@JoinColumn(name="dept_id")
    )
    private Set<Doctor> doctors=new HashSet<>();
}
