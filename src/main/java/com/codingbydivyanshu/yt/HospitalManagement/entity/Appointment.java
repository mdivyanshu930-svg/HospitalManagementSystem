package com.codingbydivyanshu.yt.HospitalManagement.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Builder


public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime appointmentTime;
    @Column(length = 500)
    private String reason;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="patient_id",nullable = false)
    private patient Patient ;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;
}
