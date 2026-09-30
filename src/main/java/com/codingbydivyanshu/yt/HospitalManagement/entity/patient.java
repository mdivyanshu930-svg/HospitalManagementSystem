package com.codingbydivyanshu.yt.HospitalManagement.entity;

import com.codingbydivyanshu.yt.HospitalManagement.type.Bloodgrouptype;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class patient {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(unique = true,nullable = false)
    private String name;
    @ToString.Exclude
    private LocalDate DOB;
    @OneToOne
    @MapsId
    private User user;
    private String email;
    @Enumerated(EnumType.STRING)
    private Bloodgrouptype bloodgroup;

    @OneToOne(cascade={CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name="patient_insurance_id")
    private Insurance insurance;
    @OneToMany(mappedBy="Patient",cascade = {CascadeType.REMOVE},orphanRemoval = true,fetch = FetchType.EAGER)
   private List<Appointment>appointments;

}

