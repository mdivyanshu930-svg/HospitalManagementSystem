package com.codingbydivyanshu.yt.HospitalManagement.service;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.Doctordto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.OnboardDoctorRequestDto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.addDoctordto;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.DoctorRepository;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.UserRepository;
import com.codingbydivyanshu.yt.HospitalManagement.config.Mapperconfigu;
import com.codingbydivyanshu.yt.HospitalManagement.entity.Doctor;
import com.codingbydivyanshu.yt.HospitalManagement.entity.User;
import com.codingbydivyanshu.yt.HospitalManagement.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<Doctordto>  getalldoctor(){
        List<Doctor> doctor=doctorRepository.findAll();
        List<Doctordto> doctordtos=doctor.stream().map(doctor1 -> modelMapper.map(doctor1,Doctordto.class)).toList();
        return doctordtos;
    }
//(hasrole('ADMIN')OR hasrole('DOCTOR'AND #doctorId==authentication.principle.id)")
    @PreAuthorize("hasrole('ADMIN')")
    public Doctordto addonboarddoctor(OnboardDoctorRequestDto onboardDoctorRequestDto) {
        User user=userRepository.findById(onboardDoctorRequestDto.getUserId()).orElseThrow();
        if(doctorRepository.existsById(onboardDoctorRequestDto.getUserId())){
            throw new IllegalArgumentException("Already a doctor");
        }
        Doctor doctor=Doctor.builder().name(onboardDoctorRequestDto.getName())
                .specilization(onboardDoctorRequestDto.getSpecialization()).user(user)
        .build();
        user.getRoleTypes().add(RoleType.DOCTOR);
        Doctor d1= doctorRepository.save(doctor);
        return modelMapper.map(d1,Doctordto.class);

    }
}
